package LearnMore.dao;

import LearnMore.entity.CourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Lee on 2017/6/20 0020.
 */
public interface CourseDetailDao extends JpaRepository<CourseDetail,Long>{

    List<CourseDetail> findByCourseName(String courseName);
}
