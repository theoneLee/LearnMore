package LearnMore.service;

import LearnMore.entity.Course;
import LearnMore.entity.CourseContent;
import LearnMore.entity.Question;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 *
 * Created by Lee on 2017/6/4 0004.
 */
public interface CourseService {

    List<Question> getJson(MultipartFile data) throws IOException;//从上传的文件中将里面的数据转化为json

    String getLinkByVideo(MultipartFile data,String courseContentName) throws IOException;//获取视频的保存路径

    
    
    Course getCourseByName(String courseName);

    void save(Course course);

    CourseContent getCourseContentById(Integer id);

    List<CourseContent> getAllCourseContent();

    void deleteCourseContentById(Integer id);

    List<Course> getAllCourse();

    Course getCourseById(Integer id);

    void deleteCourseById(Integer id);
}
