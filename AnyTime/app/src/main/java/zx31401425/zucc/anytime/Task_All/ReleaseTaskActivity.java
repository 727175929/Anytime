package zx31401425.zucc.anytime.Task_All;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import zx31401425.zucc.anytime.RegiserActivity.Register;

public class ReleaseTaskActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    EditText Taskid_Text;
    EditText Uname_Text;
    EditText TaskDetails_Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().hide();// 隐藏ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//remove notification bar  即全屏
        setContentView(R.layout.activity_release_task);
        button =(Button) findViewById(R.id.button);
        Taskid_Text = (EditText) findViewById(R.id.Edit_TaskId);
        Uname_Text = (EditText) findViewById(R.id.Edit_PublisherUName);
        TaskDetails_Text = (EditText) findViewById(R.id.Edit_TaskDetails);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            if(Taskid_Text.getText().toString().length()<=0){
                showExitDialog01("任务ID不能为空");
            }else if(Uname_Text.getText().toString().length()<=0){
                showExitDialog01("请输入发布者");
            }else if(TaskDetails_Text.getText().toString().length() <= 0){
                showExitDialog01("请输入详细的发布内容");
            }else{
                Task task = new Task();
                task.setTaskId(Taskid_Text.getText().toString());
                task.setPublisherUname(Uname_Text.getText().toString());
                task.setTaskDetails(TaskDetails_Text.getText().toString());
                sendRequestWithOkHttp_post(task);
            }
        }
    }

    private void sendRequestWithOkHttp_post(final Task task) {   //okhttp  post提交数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("TaskId",task.getTaskId())   //Taskid
                            .add("PublisherUName",task.getPublisherUname())   //发布者姓名
                            .add("TaskDetails",task.getTaskDetails())  //任务详情
                            .build();

                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://192.168.134.2:3000/ReleaseTask")
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
        List<Task> appList = gson.fromJson(jsonData, new TypeToken<List<Task>>() {}.getType());
        Task p = new Task();
        p.setState("");
        for (Task app : appList) {
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
                    showExitDialog02();
                }
                else if(response.equals("-1")){
                    showExitDialog01("发布失败，请尝试其他任务ID");
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

    private void showExitDialog02(){
        new AlertDialog.Builder(this)
                .setTitle("提示框")
                .setMessage("创建任务完成，是继续创建任务还是退出？")
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("继续创建", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Taskid_Text.setText("");
                        Uname_Text.setText("");
                        TaskDetails_Text.setText("");
                    }
                })
                .show();
    }

}
