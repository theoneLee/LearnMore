package LearnMore.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by Lee on 2017/6/7 0007.
 */
@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String senderUserName;//这个字段用于发信者自己给自己发信息的时候才需要添加，使发信者发的内容不会给其它用户也同样获取
    private String receiveUserName;
    private String content;
    private Date date;


    public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
