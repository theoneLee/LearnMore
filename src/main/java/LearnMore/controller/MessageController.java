package LearnMore.controller;

import LearnMore.entity.CommonUser;
import LearnMore.entity.Message;
import LearnMore.entity.MessageQueue;
import LearnMore.entity.Response;
import LearnMore.service.MessageService;
import LearnMore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 消息
 * 控制器
 * Created by Lee on 2017/6/7 0007.
 */
@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public Response sendMessage(@RequestParam(name = "sender")String senderName,@RequestParam(name = "receiver")String receiverName,@RequestParam(name = "content")String content){
        //根据senderName来找到User A,包装一个message，存储在user的MessageQueue中
        //根据receiverName找到User B，包装一个message，储存在user的messageQueue中，并且往B的messageQueue的Flag队列加入一个元素（提醒）
        messageService.sendMessage(senderName,receiverName,content);
        return new Response().success();
    }

    /**
     * 这里获取某一个发信人给自己的messageList
     * 提醒未读消息是有usercontroller提供的（在用户登录时，会取得messageQueue中的flag队列，然后判断是否有未读消息）
     *
     * @param senderName 发信人
     * @param receiverName 用户自己
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Response getMessageList(@RequestParam(name = "sender")String senderName,@RequestParam(name = "receiver")String receiverName){
        List<Message> rMessageList=messageService.getMessageList(senderName,receiverName);
        return new Response().success(rMessageList);
    }



    @RequestMapping(value = "/flag",method = RequestMethod.GET)
    public Response getFlagList(@RequestParam(name = "receiver")String receiverName){
        List<String> flagList=messageService.getFlagList(receiverName);
        return new Response().success(flagList);
    }

}
