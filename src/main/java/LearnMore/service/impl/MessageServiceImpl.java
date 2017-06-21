package LearnMore.service.impl;

import LearnMore.dao.FlagDao;
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

    @Autowired
    private FlagDao flagDao;


    @Override
    public void sendMessage(String senderName, String receiverName, String content) {
        //根据senderName来找到User A,包装一个message，存储在user的MessageList中
        //根据receiverName找到User B，包装一个message，储存在user的MessageList中，并且往B的flagList加入一个元素（提醒）
        CommonUser senderMessage=userDao.findByUsernameFetchMessageList(senderName);
        List<Message>messageList=senderMessage.getMessageList();
        Message sMessage=new Message();
//        sMessage.setReceiveUserName(receiverName);//不能写成这个。写了这个就是相当于给每一个人都给彼此发送一条消息
        sMessage.setReceiveUserName(senderName);
        sMessage.setContent(content);
        sMessage.setDate(new Date());
        sMessage.setSenderUserName(receiverName);//为下面getMessageList提供便利
        messageList.add(sMessage);
        userDao.save(senderMessage);


        CommonUser receiverMessage=userDao.findByUsernameFetchMessageList(receiverName);
        CommonUser receiverFlag=userDao.findByUsernameFetchFlagList(receiverName);
        Message rMessage=new Message();
        rMessage.setReceiveUserName(senderName);
        rMessage.setContent(content);
        rMessage.setDate(new Date());
        List<Message> messageList1=receiverMessage.getMessageList();
        messageList1.add(rMessage);
        List<Flag> flagList=receiverFlag.getFlagList();
        //**********给receiver添加一个Flag（提示未读），并且sender同一段时间内连续发送多条消息，也不会往FlagList中添加多条，从而导致前端遍历这个队列会出现多个相同用户
        boolean res=true;
        for (Flag flag:flagList){
            if (flag.getName().equals(senderName)){
                res=false;
            }
        }
        if (res){
            flagList.add(new Flag(senderName));
        }
        //************************
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
            //System.out.println(m.getReceiveUserName());
            if (m.getReceiveUserName().equals(senderName)){
                res.add(m);
            }
            if (m.getSenderUserName()!=null){
                if (m.getSenderUserName().equals(senderName)&&m.getReceiveUserName().equals(receiverName)){//使发信者发的内容不会给其它用户也同样获取
                    res.add(m);
                }
            }

        }
        return res;
    }

    @Override
    public void clearFlagList(String senderName, String receiverName) {
        //清除receiver的flagList队列的sender
        CommonUser receiver=userDao.findByUsernameFetchFlagList(receiverName);
        //Flag temp=null;
        List<Flag> flagList=receiver.getFlagList();
        for (Flag flag:flagList){
            if (flag.getName().equals(senderName)){
                //temp=flag;
                flagList.remove(flag);
                break;
            }
        }
        userDao.save(receiver);
//        if (temp!=null){//没有这个，不会删除数据库应该被清理的flag//设置级联detach
//            flagDao.delete(temp);
//        }

    }
}
