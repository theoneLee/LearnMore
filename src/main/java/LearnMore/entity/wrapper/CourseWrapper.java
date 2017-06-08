package LearnMore.entity.wrapper;

import org.springframework.web.multipart.MultipartFile;

/**
 * 包装类。
 * 用于接受前端的数据然后进行包装后转化为持久化类
 * Created by Lee on 2017/6/1 0001.
 */
public class CourseWrapper {

    private String courseName;
    private String teacherTeam;
    private String courseIntroduction;
    private String courseOutline;
    private MultipartFile exam;


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

    public MultipartFile getExam() {
        return exam;
    }

    public void setExam(MultipartFile exam) {
        this.exam = exam;
    }
}
