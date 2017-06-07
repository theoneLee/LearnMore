package LearnMore.service.impl;

import LearnMore.dao.UserDao;
import LearnMore.entity.Message;
import LearnMore.entity.MessageQueue;
import LearnMore.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Lee on 2017/6/7 0007.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageQueueDao messageQueueDao;



    @Override
    public MessageQueue getMessageQueueByUserName() {
        return null;
    }

    @Override
    public void sendMessage(String senderName, String receiveName, String content) {

    }

    @Override
    public List<String> getFlagList(String receiverName) {
        return null;
    }

    @Override
    public List<Message> getMessageList(String senderName, String receiverName) {
        return null;
    }
}
