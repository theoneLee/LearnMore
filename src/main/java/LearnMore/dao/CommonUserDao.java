package LearnMore.dao;

import LearnMore.entity.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * Created by Lee on 2017/6/4 0004.
 */
public interface CommonUserDao extends JpaRepository<CommonUser,Long>{

    CommonUser findByUsername(String username);


}
