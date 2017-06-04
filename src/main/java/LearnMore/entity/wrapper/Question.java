package LearnMore.entity.wrapper;

/**
 * 考试题目和作业题目模版
 * Created by Lee on 2017/6/4 0004.
 */
public class Question {
    private int number;//题号
    private String title;//题目
    private String option;//选项

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
