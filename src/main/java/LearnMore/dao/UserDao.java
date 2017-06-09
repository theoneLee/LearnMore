package LearnMore.dao;

import LearnMore.entity.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * Created by Lee on 2017/6/5 0005.
 */
public interface UserDao extends JpaRepository<CommonUser,Long>{

    CommonUser findByUsername(String formName);

    @Query("select c from CommonUser c left join fetch c.courseDetailList where c.username=?1")
    CommonUser findByUsernameFetchCourseDetail(String username);


    @Query("select c from CommonUser c left join fetch c.messageQueue where c.username=?1")
    CommonUser findByUsernameFetchMessageQueue(String username);
}
