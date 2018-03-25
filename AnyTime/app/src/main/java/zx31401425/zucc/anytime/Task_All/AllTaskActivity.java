package zx31401425.zucc.anytime.Task_All;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zx31401425.zucc.anytime.R;

public class AllTaskActivity extends AppCompatActivity {

    private List<Task> taskList = new ArrayList<Task>();
    private ListView listView;
    private TaskAdapterer adapterer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().hide();// 隐藏ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//remove notification bar  即全屏
        setContentView(R.layout.activity_all_task);
        initTask();   //初始化数据
        adapterer = new TaskAdapterer(AllTaskActivity.this,R.layout.task_item,taskList);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapterer);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(position != 0) {
                    Task task = taskList.get(position); //任务ID：task.getTaskId()
                    Toast.makeText(AllTaskActivity.this, task.getTaskId(), Toast.LENGTH_SHORT).show();
                    showExitDialog02(task.getTaskId(),position);
                    //taskList.remove(position);    //删除指定LIST
                    //adapterer.notifyDataSetChanged();   //刷新
                }
            }
        });
        sendRequestWithOkHttp();
    }

    private void showExitDialog02(final String id,int i){
        new AlertDialog.Builder(this)
                .setTitle("提示框")
                .setMessage("确定要接受该任务吗？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ReceiveTask(id,i);
            }
        })
                .setNegativeButton("否", null)
                .show();
    }

    private void ReceiveTask(String id,int i){
        //taskList.remove(i);    //删除指定LIST
        taskList.clear(); //删除所有的LIST
        adapterer.notifyDataSetChanged();   //刷新
        initTask();
        UpdateTask(id);
    }

    private void UpdateTask(final String id){   //先更新对应的任务  然后返回其他所有符合条件的Task然后再给LISTVIEW
        //okhttp  get方式获取服务器数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://192.168.134.2:3000/AllTask/get/"+id)
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

    private void initTask(){
            Task task1 = new Task("ID","发布者","详情","接受者","状态");
            taskList.add(task1);
    }

    private void sendRequestWithOkHttp() {   //okhttp  get方式获取服务器数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址是电脑本机
                            .url("http://192.168.134.2:3000/AllTask/get/ALL")
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
        List<Task> appList = gson.fromJson(jsonData, new TypeToken<List<Task>>() {}.getType());
        for (Task app : appList) {
            showResponse(app);
            //taskList.add(app);
        }
    }

    private void showResponse(final Task task) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作，将结果显示到界面上
                Task task1 = new Task(task.getTaskId(),task.getPublisherUname(),task.getTaskDetails(),task.getReceiveUname(),task.getTaskState());
                taskList.add(task1);
                adapterer.notifyDataSetChanged();
            }
        });
    }
}
