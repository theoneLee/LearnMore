package LearnMore.controller;

import LearnMore.entity.*;
import LearnMore.security.IgnoreSecurity;
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
        messageService.sendMessage(senderName,receiverName,content);
        return new Response().success();
    }

    /**
     * 这里获取某一个发信人给自己的messageList
     * 提醒未读消息是有usercontroller提供的（在用户登录时，会取得user中的flag队列，然后判断是否有未读消息）
     *
     * @param senderName 发信人
     * @param receiverName 用户自己
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @IgnoreSecurity
    public Response getMessageList(@RequestParam(name = "sender")String senderName,@RequestParam(name = "receiver")String receiverName){
        List<Message> rMessageList=messageService.getMessageList(senderName,receiverName);
        System.out.println(rMessageList);
        if (rMessageList==null){
            return new Response().success("no message");
        }
        return new Response().success(rMessageList);
    }



    @RequestMapping(value = "/flag",method = RequestMethod.GET)
    @IgnoreSecurity
    public Response getFlagList(@RequestParam(name = "receiver")String receiverName){
        List<Flag> flagList=messageService.getFlagList(receiverName);
        return new Response().success(flagList);
    }

}
