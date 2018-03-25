package zx31401425.zucc.anytime.Task_All;

/**
 * Created by 赵轩 on 2018/3/22.
 */

public class Task {
    private String TaskId;
    private String PublisherUName;
    private String TaskState;
    private String TaskDetails;
    private String ReceiveUName;
    private String State;  //任务返回

    public Task() {

    }

    public String getReceiveUname() {
        return ReceiveUName;
    }

    public void setReceiveUname(String receiveUname) {
        ReceiveUName = receiveUname;
    }

    public String getTaskId() {
        return TaskId;
    }

    public String getPublisherUname() {
        return PublisherUName;
    }

    public String getTaskState() {
        return TaskState;
    }

    public String getTaskDetails() {
        return TaskDetails;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public void setPublisherUname(String publisherUname) {
        PublisherUName = publisherUname;
    }

    public void setTaskState(String taskState) {
        TaskState = taskState;
    }

    public void setTaskDetails(String taskDetails) {
        TaskDetails = taskDetails;
    }

    public  Task(String TaskId,String PublisherUname,String TaskDetails,String ReceiveUname,String TaskState){
        this.TaskId = TaskId;
        this.PublisherUName = PublisherUname;
        this.TaskState = TaskState;
        this.TaskDetails = TaskDetails;
        this.ReceiveUName = ReceiveUname;
    }

    public void setState(String state) {
        this.State = state;
    }

    public String getState() {
        return State;
    }
}
