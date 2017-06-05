package LearnMore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程实体
 * Created by Lee on 2017/6/1 0001.
 */
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Integer id;
    private String courseName;//课程名称
    private String teacherTeam;//教学团队
    private String courseIntroduction;//课程介绍
    private String courseOutline;//教学大纲

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private List<Question> courseExamJson;//将上传的txt考试题目，里面的每一道题解析成Question，然后存储在这里

    @OneToMany(mappedBy = "course",cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private List<CourseContent> courseContentList=new ArrayList<>();


    public List<Question> getCourseExamJson() {
        return courseExamJson;
    }

    public void setCourseExamJson(List<Question> courseExamJson) {
        this.courseExamJson = courseExamJson;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherTeam() {
        return teacherTeam;
    }

    public void setTeacherTeam(String teacherTeam) {
        this.teacherTeam = teacherTeam;
    }

    public String getCourseIntroduction() {
        return courseIntroduction;
    }

    public void setCourseIntroduction(String courseIntroduction) {
        this.courseIntroduction = courseIntroduction;
    }

    public String getCourseOutline() {
        return courseOutline;
    }

    public void setCourseOutline(String courseOutline) {
        this.courseOutline = courseOutline;
    }

    public List<CourseContent> getCourseContentList() {
        return courseContentList;
    }

    public void setCourseContentList(List<CourseContent> courseContentList) {
        this.courseContentList = courseContentList;
    }
}
