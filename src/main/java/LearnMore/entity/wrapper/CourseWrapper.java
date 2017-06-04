package LearnMore.entity.wrapper;

import org.springframework.web.multipart.MultipartFile;

/**
 * todo
 * 包装类。
 * 用于接受前端的数据然后进行包装后转化为持久化类
 * Created by Lee on 2017/6/1 0001.
 */
public class CourseWrapper {

    private String courseName;//课程名称
    private String teacherTeam;//教学团队
    private String courseIntroduction;//课程介绍
    private String courseOutline;//教学大纲

    //private MultipartFile[] files;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

}
