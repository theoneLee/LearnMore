package LearnMore.service;

import LearnMore.entity.Flag;
import LearnMore.entity.Message;

import java.util.List;

/**
 * 私信
 * 业务接口
 * Created by Lee on 2017/6/7 0007.
 */
public interface MessageService {

    void sendMessage(String senderName,String receiveName,String content);

    List<Flag> getFlagList(String receiverName);

    List<Message> getMessageList(String senderName, String receiverName);
}
