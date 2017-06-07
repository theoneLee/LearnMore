package LearnMore.service.impl;

import LearnMore.dao.UserDao;
import LearnMore.entity.CommonUser;
import LearnMore.entity.Message;
import LearnMore.entity.MessageQueue;
import LearnMore.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by Lee on 2017/6/7 0007.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserDao userDao;



    @Override
    public void sendMessage(String senderName, String receiverName, String content) {
        //根据senderName来找到User A,包装一个message，存储在user的MessageQueue中
        //根据receiverName找到User B，包装一个message，储存在user的messageQueue中，并且往B的messageQueue的Flag队列加入一个元素（提醒）
        CommonUser sender=userDao.findByUsernameFetchMessageQueue(senderName);
        Message sMessage=new Message();
        sMessage.setReceiveUserName(receiverName);
        sMessage.setContent(content);
        sMessage.setDate(new Date());
        sender.getMessageQueue().getMessageList().add(sMessage);
        userDao.save(sender);

        CommonUser receiver=userDao.findByUsernameFetchMessageQueue(receiverName);
        Message rMessage=new Message();
        rMessage.setReceiveUserName(senderName);
        rMessage.setContent(content);
        rMessage.setDate(new Date());
        receiver.getMessageQueue().getMessageList().add(rMessage);
        receiver.getMessageQueue().getFlagList().add(senderName);
        userDao.save(receiver);

    }

    @Override
    public List<String> getFlagList(String receiverName) {
        CommonUser receiver=userDao.findByUsernameFetchMessageQueue(receiverName);
        return receiver.getMessageQueue().getFlagList();
    }

    @Override
    public List<Message> getMessageList(String senderName, String receiverName) {
        CommonUser receiver=userDao.findByUsernameFetchMessageQueue(receiverName);
        List<Message> list=receiver.getMessageQueue().getMessageList();
        List<Message> res=new ArrayList<>();
        for (Message m:list) {
            if (m.getReceiveUserName().equals(senderName)){
                res.add(m);
            }
        }
        return res;
    }
}
