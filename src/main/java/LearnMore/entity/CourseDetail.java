package LearnMore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Created by Lee on 2017/6/7 0007.
 */
@Entity
public class CourseDetail {
    @Id
    @GeneratedValue
    private Long id;
    private String courseName;
    private String score;
//    @ManyToMany()
//    private CommonUser user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
