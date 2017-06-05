package LearnMore.dao;

import LearnMore.entity.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * Created by Lee on 2017/6/5 0005.
 */
public interface UserDao extends JpaRepository<CommonUser,Long>{

    CommonUser findByUsername(String formName);
}
