package LearnMore.dao;

import LearnMore.entity.Course;
import LearnMore.entity.CourseContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Lee on 2017/6/4 0004.
 */
public interface CourseContentDao extends JpaRepository<CourseContent,Long>{

    CourseContent findByCourse(Course course);

    /**
     * 试试这样能不能查，不行就用上面那个查两次吧。
     * @param courseName
     * @return
     */
    @Query("select c from CourseContent c where c.course.courseName=?1")
    CourseContent findByCourseQuery(String courseName);
}
