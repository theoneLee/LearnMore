package LearnMore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2017/6/3 0003.
 */
@Entity
public class CommonUser {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String permission;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private List<CourseDetail> courseDetailList=new ArrayList<>();//todo 该字段用来给学生选择课程，以及登记考试后的课程分数

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private MessageQueue messageQueue;//todo 私信模块

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<CourseDetail> getCourseDetailList() {
        return courseDetailList;
    }

    public void setCourseDetailList(List<CourseDetail> courseDetailList) {
        this.courseDetailList = courseDetailList;
    }

    public MessageQueue getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

}
