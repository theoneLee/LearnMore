package LearnMore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 考试题目和作业题目模版
 * Created by Lee on 2017/6/4 0004.
 */
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private int number;//题号
    private String title;//题目 所有题目都是判断题
    private String answer;//答案

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
