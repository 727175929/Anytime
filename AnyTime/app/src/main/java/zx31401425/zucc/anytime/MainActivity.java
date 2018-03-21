package zx31401425.zucc.anytime;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true
    EditText Upass_Tets;
    EditText Uname_Text;
    Button Register_Button;
    Button Login_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().hide();// 隐藏ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//remove notification bar  即全屏
        setContentView(R.layout.activity_main);
        Login_Button = (Button) findViewById(R.id.login_button);
        Login_Button.setOnClickListener(this);
        Register_Button = (Button) findViewById(R.id.register_button);
        Register_Button.setOnClickListener(this);
        Uname_Text = (EditText) findViewById(R.id.login_id);
        Upass_Tets = (EditText) findViewById(R.id.login_password);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_button) {
            if(Uname_Text.getText().toString().length()<=0){
                showExitDialog01("账号不能为空");
            }
            else if(Upass_Tets.getText().toString().length()<=0){
                showExitDialog01("密码不能为空");
            }
            else
            sendRequestWithOkHttp(Uname_Text.getText().toString());
        }
        else if(view.getId() == R.id.register_button){

        }
    }
    private void sendRequestWithOkHttp(final String Uname) {   //okhttp  get方式获取服务器数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://192.168.134.2:3000/Users/get/"+Uname)
                            //  .url("http://10.0.2.2/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
//                      parseJSONWithJSONObject(responseData);
//                    parseXMLWithSAX(responseData);
//                    parseXMLWithPull(responseData);
//                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {   //解析服务器获取来的Json数据
        Gson gson = new Gson();
        List<Users_Pass> appList = gson.fromJson(jsonData, new TypeToken<List<Users_Pass>>() {}.getType());
        Users_Pass p = new Users_Pass();
        p.setUpass("");
        for (Users_Pass app : appList) {
            Log.d("MainActivity", "name is " + app.getUpass());
            p=app;
        }
        showResponse(p.getUpass());
    }
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                if(response.equals(Upass_Tets.getText().toString())){
                    showExitDialog01("登录成功");
                }
                else if(response.equals("")){
                    showExitDialog01("账号不存在");
                }
                else {
                    showExitDialog01("密码错误");
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
