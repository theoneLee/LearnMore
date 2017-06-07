package LearnMore.service;

import LearnMore.entity.Message;
import LearnMore.entity.MessageQueue;

import java.util.List;

/**
 * 私信
 * 业务接口
 * Created by Lee on 2017/6/7 0007.
 */
public interface MessageService {
    MessageQueue getMessageQueueByUserName();

    void sendMessage(String senderName,String receiveName,String content);

    List<String> getFlagList(String receiverName);

    List<Message> getMessageList(String senderName, String receiverName);
}
