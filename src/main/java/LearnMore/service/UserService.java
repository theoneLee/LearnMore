package LearnMore.service;

import LearnMore.dao.UserDao;
import LearnMore.entity.CommonUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lee on 2017/6/5 0005.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public void signIn(CommonUser user) {
        userDao.save(user);
    }

    public CommonUser checkUserPassword(CommonUser user) {
        String formName=user.getUsername();
        String formPassword=user.getPassword();

        CommonUser userDb=userDao.findByUsername(formName);
        String truePassword=userDb.getPassword();
        if (truePassword!=null&&truePassword.equals(formPassword)){
            return userDb;
        }
        return null;
    }


}
