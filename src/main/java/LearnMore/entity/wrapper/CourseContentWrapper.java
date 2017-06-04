package LearnMore.entity.wrapper;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Lee on 2017/6/4 0004.
 */
public class CourseContentWrapper {
    private String courseContentName;//该视频内容名
    private MultipartFile[] files;

    public String getCourseContentName() {
        return courseContentName;
    }

    public void setCourseContentName(String courseContentName) {
        this.courseContentName = courseContentName;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}
