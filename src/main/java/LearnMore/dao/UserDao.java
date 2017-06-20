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




//    @Query("select c from CommonUser c left join fetch c.messageQueue where c.username=?1")
//    CommonUser findByUsernameFetchMessageQueue(String username);


    //c.messageList.date可以使用
    @Query("select c from CommonUser c left join fetch c.messageList where c.username=?1 order by c.messageList.date asc ")
    CommonUser findByUsernameFetchMessageList(String senderName);

    @Query("select c from CommonUser c left join fetch c.flagList where c.username=?1")
    CommonUser findByUsernameFetchFlagList(String receiverName);
}
