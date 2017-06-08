package LearnMore.entity.wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2017/6/8 0008.
 */
public class ExamParamWrapper {
    private String courseName;
    private List<String> optionList=new ArrayList<>();

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }
}
