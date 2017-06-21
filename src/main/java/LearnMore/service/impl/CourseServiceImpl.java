package LearnMore.service.impl;

import LearnMore.dao.CourseContentDao;
import LearnMore.dao.CourseDao;
import LearnMore.dao.CourseDetailDao;
import LearnMore.entity.*;
import LearnMore.entity.wrapper.ExamParamWrapper;
import LearnMore.service.CourseService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2017/6/5 0005.
 */
@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseDetailDao courseDetailDao;

    @Autowired
    private CourseContentDao courseContentDao;

    @Override
    public List<Question> getJson(MultipartFile data) throws IOException{
        //一行一行读，题目序号，题目，正确答案
        //然后将数据封装成Question,不断循环直到文件结束
        InputStream is=data.getInputStream();
        BufferedReader reader =new BufferedReader(new InputStreamReader(is));
        String str = null;
        List<Question> list=new ArrayList<>();
        Question q=new Question();
        int flag=0;
        while (true) {
            str = reader.readLine();
            if(str!=null){
                switch (flag){
                    case 0:
                        q.setNumber(Integer.parseInt(str));
                        flag++;
                        break;
                    case 1:
                        q.setTitle(str);
                        flag++;
                        break;
                    case 2:
                        q.setAnswer(str);
                        list.add(q);
                        q=new Question();//重置状态
                        flag=0;
                        break;
                }
            }else {
                break;
            }
        }
        reader.close();
        is.close();
        return list;
    }

    @Override
    public String getLinkByVideo(MultipartFile data,String courseContentName) throws IOException{
        //将视频文件保存在一个地方并返回访问该文件url路径
        //todo 这里可以考虑把视频编码处理下，做一下不影响视频质量的压缩
        System.out.println("视频："+data.getOriginalFilename());
        FileUtils.writeByteArrayToFile(new File("target/classes/static/video/"+data.getOriginalFilename()),data.getBytes());

        return "/video/"+data.getOriginalFilename();
    }

    @Override
    public Course getCourseByNameFetchCourseContentList(String courseName) {
        return courseDao.findByCourseNameFetchCourseContentList(courseName);
    }

    @Override
    public Course getCourseByName(String courseName) {

        return courseDao.findByCourseName(courseName);
    }

    @Override
    public void save(Course course){
        courseDao.save(course);
    }

    @Override
    public CourseContent getCourseContentByccId(Integer ccid) {
        return courseContentDao.findOne(ccid);
    }

    @Override
    public List<CourseContent> getCourseContentListByCourse(String courseName) {
        Course c=courseDao.findByCourseName(courseName);
        return courseContentDao.findByCourse(c);
    }

    @Override
    public List<CourseContent> getAllCourseContent() {
        return courseContentDao.findAll();
    }

    @Override
    public void deleteCourseContentById(Integer id) {
        courseContentDao.deleteById(id);
    }

    @Override
    public List<Course> getAllCourse() {
        List<Course> list=courseDao.findAll();
        for (Course course:list){
            course.setCourseContentList(null);
            course.setCourseExamJson(null);
        }
        return list;
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseDao.findById(id);
    }

    @Override
    public Course getCourseByIdFetchExam(Integer id){
        return courseDao.findByIdFetchExam(id);
    }

    @Override
    public void deleteCourseById(Integer id) {
        courseDao.deleteById(id);
    }

    @Override
    public String checkExamAndGetPoint(ExamParamWrapper wrapper) {
        System.out.println("执行函数checkExamAndGetPoint");
        Course course=courseDao.findByCourseNameFetchExam(wrapper.getCourseName());
        List<Question> trueList=course.getCourseExamJson();
        List<String> optionList=wrapper.getOptionList();
        double flag=0;
        for (int i = 0; i < trueList.size(); i++) {
            Question trueQuestion=trueList.get(i);
            String option=optionList.get(i);
            if (option.equals(trueQuestion.getAnswer())){
                flag=flag+1;
            }
        }
        double score=flag/trueList.size();
        return String.valueOf(score);

    }

    @Override
    public void saveAdd(Course course) throws Exception {
        Course temp=courseDao.findByCourseName(course.getCourseName());
        if (temp!=null){
            throw new Exception("add_course_failure");
        }
        courseDao.save(course);
    }

    @Override
    public List<String> getCourUserListByCourseName(String courseName) {
        List<CourseDetail> list=courseDetailDao.findByCourseName(courseName);
        List<String> res=new ArrayList<>();
        for (CourseDetail c:list){
            CommonUser user=c.getUser();
            res.add(user.getUsername());
        }
        return res;
    }
}
