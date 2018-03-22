package zx31401425.zucc.anytime.RegiserActivity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zx31401425.zucc.anytime.R;
import zx31401425.zucc.anytime.Users_Pass;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    Button RegisterButton;
    EditText Uname;
    EditText Upass1;
    EditText Upass2;
    EditText Uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().hide();// 隐藏ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//remove notification bar  即全屏
        setContentView(R.layout.activity_register);
        Uname = (EditText) findViewById(R.id.Uname);
        Upass1 = (EditText) findViewById(R.id.login_password1);
        Upass2 = (EditText) findViewById(R.id.login_password2);
        Uid = (EditText) findViewById(R.id.login_id);
        RegisterButton = (Button) findViewById(R.id.register_button);
        RegisterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.register_button){
            if(Uid.getText().toString().length()<=0){
                showExitDialog01("账号不能为空");
            }else if(Upass1.getText().toString().length()<=0){
                showExitDialog01("密码不能为空");
            }else if(Uname.getText().toString().length()<=0){
                showExitDialog01("名字不能为空");
            }
            else if(!Upass1.getText().toString().equals(Upass2.getText().toString())){
                showExitDialog01("两次密码输入不相同");
            }else {
                Register register = new Register();
                register.setUid(Uid.getText().toString());
                register.setUpass(Upass1.getText().toString());
                register.setUname(Uname.getText().toString());
                sendRequestWithOkHttp_post(register);
            }
        }
    }

    private void sendRequestWithOkHttp_post(final Register register) {   //okhttp  post提交数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("UName",register.getUid())   //账号
                            .add("Upass",register.getUpass())   //密码
                            .add("UName2",register.getUname())  //姓名
                            //.add("UName_Latitude","0")  // 维度
                            //.add("UName_longitude","0")  // 经度
                            .build();

                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://192.168.56.1:3000/Users/save")
                            .post(requestBody)
                            //  .url("http://10.0.2.2/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
 //                     parseJSONWithJSONObject(responseData);
//                    parseXMLWithSAX(responseData);
//                    parseXMLWithPull(responseData);
//                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<Register> appList = gson.fromJson(jsonData, new TypeToken<List<Register>>() {}.getType());
        Register p = new Register();
        p.setState("");
        for (Register app : appList) {
            p = app;
        }
        showResponse(p.getState());
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                if(response.equals("1")){
                    showExitDialog01("注册成功");
                }
                else if(response.equals("-1")){
                    showExitDialog01("注册失败，账号已存在");
                }
            }
        });
    }
    private void showExitDialog01(String A){
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage(A)
                .setPositiveButton("确定", null)
                .show();
    }
}
