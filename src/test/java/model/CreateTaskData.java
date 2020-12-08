package model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTaskData {

    public String taskTittle;
    public String taskDescription;
    public String emailOwner;
    public String emailAssign;
    public String type;

    public String idTask;

    public CreateTaskData(){};

    public CreateTaskData(String taskTittle, String taskDescription, String emailOwner, String emailAssign) {
        this.taskTittle = taskTittle;
        this.taskDescription = taskDescription;
        this.emailOwner = emailOwner;
        this.emailAssign = emailAssign;
    }

    @JsonGetter("task_title")
    public String getTaskTittle() {
        return taskTittle;
    }

    @JsonGetter("task_description")
    public String getTaskDescription() {
        return taskDescription;
    }

    @JsonGetter("email_owner")
    public String getEmailOwner() {
        return emailOwner;
    }

    @JsonGetter("email_assign")
    public String getEmailAssign() {
        return emailAssign;
    }

    @JsonGetter("type")
    public String getType() {
        return type;
    }

    @JsonGetter("id_task")
    public String getIdTask() {
        return idTask;
    }
}
