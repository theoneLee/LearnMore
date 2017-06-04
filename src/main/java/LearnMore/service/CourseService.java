package LearnMore.service;

import LearnMore.entity.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * Created by Lee on 2017/6/4 0004.
 */
public interface CourseService {

    String getJson(MultipartFile data);//从上传的文件中将里面的数据转化为json


    String getLinkByVideo(MultipartFile data);//获取视频的保存路径
}
