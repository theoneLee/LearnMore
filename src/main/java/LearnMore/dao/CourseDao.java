package LearnMore.dao;

import LearnMore.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Lee on 2017/6/4 0004.
 */
public interface CourseDao extends JpaRepository<Course,Long>{
    Course findByCourseName(String name);

    /**
     * 查找Course（携带exam）
     * @param name
     * @return
     */
    @Query("select c from Course c join fetch c.courseExamJson where c.courseName=?1")
    Course findByCourseNameFetchExam(String name);


}
