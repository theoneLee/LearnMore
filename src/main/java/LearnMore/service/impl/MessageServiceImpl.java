package LearnMore.service.impl;

import LearnMore.dao.UserDao;
import LearnMore.entity.CommonUser;
import LearnMore.entity.Flag;
import LearnMore.entity.Message;
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
        //根据senderName来找到User A,包装一个message，存储在user的MessageList中
        //根据receiverName找到User B，包装一个message，储存在user的MessageList中，并且往B的flagList加入一个元素（提醒）
        CommonUser sender=userDao.findByUsernameFetchMessageList(senderName);
        List<Message>messageList=sender.getMessageList();
        Message sMessage=new Message();
        sMessage.setReceiveUserName(receiverName);
        sMessage.setContent(content);
        sMessage.setDate(new Date());
        messageList.add(sMessage);
        userDao.save(sender);


        CommonUser receiverMessage=userDao.findByUsernameFetchMessageList(receiverName);
        CommonUser receiverFlag=userDao.findByUsernameFetchFlagList(receiverName);
        Message rMessage=new Message();
        rMessage.setReceiveUserName(senderName);
        rMessage.setContent(content);
        rMessage.setDate(new Date());
        List<Message> messageList1=receiverMessage.getMessageList();
        messageList1.add(rMessage);
        List<Flag> flagList=receiverFlag.getFlagList();
        flagList.add(new Flag(senderName));

        userDao.save(receiverMessage);
        userDao.save(receiverFlag);

    }

    @Override
    public List<Flag> getFlagList(String receiverName) {
        CommonUser receiver=userDao.findByUsernameFetchFlagList(receiverName);
        return receiver.getFlagList();
    }

    @Override
    public List<Message> getMessageList(String senderName, String receiverName) {
        CommonUser receiver=userDao.findByUsernameFetchMessageList(receiverName);
        List<Message> list=receiver.getMessageList();
        List<Message> res=new ArrayList<>();
        for (Message m:list) {
            if (m.getReceiveUserName().equals(senderName)){
                res.add(m);
            }
        }
        return res;
    }
}
