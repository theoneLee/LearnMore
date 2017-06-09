package LearnMore.entity.wrapper;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Lee on 2017/6/4 0004.
 */
public class CourseContentWrapper {
    private String courseContentName;
    private String courseName;
    private MultipartFile video;
    private MultipartFile homework;

    public String getCourseContentName() {
        return courseContentName;
    }

    public void setCourseContentName(String courseContentName) {
        this.courseContentName = courseContentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public MultipartFile getVideo() {
        return video;
    }

    public void setVideo(MultipartFile video) {
        this.video = video;
    }

    public MultipartFile getHomework() {
        return homework;
    }

    public void setHomework(MultipartFile homework) {
        this.homework = homework;
    }
}
