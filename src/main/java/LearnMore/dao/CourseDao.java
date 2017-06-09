package LearnMore.dao;

import LearnMore.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Lee on 2017/6/4 0004.
 */
public interface CourseDao extends JpaRepository<Course,Long>{
    Course findByCourseName(String name);

    @Query("select c from Course c left join fetch c.courseContentList where c.courseName=?1")
    Course findByCourseNameFetchCourseContentList(String name);

    /**
     * 查找Course（携带exam）
     * @param name
     * @return
     */
    @Query("select c from Course c join fetch c.courseExamJson where c.courseName=?1")
    Course findByCourseNameFetchExam(String name);

    /**
     * 查找Course（携带exam）
     * @return
     */
    @Query("select c from Course c join fetch c.courseExamJson where c.id=?1")
    Course findByIdFetchExam(Integer id);

    Course findById(Integer id);

    void deleteById(Integer id);
}
