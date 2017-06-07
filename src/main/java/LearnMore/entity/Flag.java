package LearnMore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Lee on 2017/6/7 0007.
 */
@Entity
public class Flag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Flag() {
    }

    public Flag(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
