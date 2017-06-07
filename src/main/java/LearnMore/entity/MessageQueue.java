package LearnMore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2017/6/7 0007.
 */
@Entity
public class MessageQueue {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<Message> messageList=new ArrayList<>();

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<String> flagList=new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "user_id")
    private CommonUser commonUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<String> getFlagList() {
        return flagList;
    }

    public void setFlagList(List<String> flagList) {
        this.flagList = flagList;
    }

    public CommonUser getCommonUser() {
        return commonUser;
    }

    public void setCommonUser(CommonUser commonUser) {
        this.commonUser = commonUser;
    }
}
