package LearnMore.service;

import LearnMore.dao.UserDao;
import LearnMore.entity.CommonUser;
import LearnMore.entity.CourseDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lee on 2017/6/5 0005.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;



    public void signIn(CommonUser user) throws Exception{
        CommonUser u=userDao.findByUsername(user.getUsername());
        if (u!=null){
            throw new Exception("signin_failure");
        }
        userDao.save(user);
    }

    public CommonUser checkUserPassword(CommonUser user) throws Exception{
        String formName=user.getUsername();
        String formPassword=user.getPassword();

        CommonUser userDb=userDao.findByUsername(formName);
        if (userDb==null){
            throw new Exception("username_no_exist");
        }
        String truePassword=userDb.getPassword();

        if (truePassword.equals(formPassword)){
            return userDb;
        }
        throw new Exception("password_incorrect");
    }


    public List<CourseDetail> getCourseDetailList(String username) {
        CommonUser user=userDao.findByUsernameFetchCourseDetail(username);
        return user.getCourseDetailList();
    }

    public void addCourseDetail(String username, String courseName) {
        CommonUser user=userDao.findByUsernameFetchCourseDetail(username);
        CourseDetail courseDetail=new CourseDetail();
        courseDetail.setCourseName(courseName);
        courseDetail.setScore("未考试");
        user.getCourseDetailList().add(courseDetail);
        userDao.save(user);
    }

    public void updateCourseDetailForExam(String userName, String courseName,String res) {
        CommonUser user=userDao.findByUsernameFetchCourseDetail(userName);
        for (CourseDetail c:user.getCourseDetailList()){
            if ( c.getCourseName().equals(courseName)){
                c.setScore(res);
                break;
            }
        }
        userDao.save(user);
    }
}
