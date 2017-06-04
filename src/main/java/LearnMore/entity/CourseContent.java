package LearnMore.entity;

import javax.persistence.*;

/**
 * 课程内容实体
 * Created by Lee on 2017/6/4 0004.
 */
@Entity
public class CourseContent {
    @Id
    @GeneratedValue
    private Integer id;

    private String courseContentName;//该视频内容名
    private String courseVideoLink;//保存教学视频的路径

    private String courseHomeWorkJson;//将上传的txt作业题目，解析成json然后存储在这里

    private String courseHomeWorkAnswerJson;//将上传的txt作业答案，解析成json然后存储在这里



    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "course_id")
    private Course course;


    public String getCourseContentName() {
        return courseContentName;
    }

    public void setCourseContentName(String courseContentName) {
        this.courseContentName = courseContentName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseVideoLink() {
        return courseVideoLink;
    }

    public void setCourseVideoLink(String courseVideoLink) {
        this.courseVideoLink = courseVideoLink;
    }

    public String getCourseHomeWorkJson() {
        return courseHomeWorkJson;
    }

    public void setCourseHomeWorkJson(String courseHomeWorkJson) {
        this.courseHomeWorkJson = courseHomeWorkJson;
    }

    public String getCourseHomeWorkAnswerJson() {
        return courseHomeWorkAnswerJson;
    }

    public void setCourseHomeWorkAnswerJson(String courseHomeWorkAnswerJson) {
        this.courseHomeWorkAnswerJson = courseHomeWorkAnswerJson;
    }
}
