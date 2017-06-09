package LearnMore.dao;

import LearnMore.entity.CommonUser;
import LearnMore.entity.MessageQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Lee on 2017/6/7 0007.
 */
public interface MessageQueueDao extends JpaRepository<MessageQueue,Long>{

    @Query("select m from MessageQueue m left join fetch m.flagList where m.commonUser=?1")
    MessageQueue findByCommonUserFetchFlagList(CommonUser receiver);

    @Query("select m from MessageQueue m left join fetch m.messageList where m.commonUser=?1")
    MessageQueue findByCommonUserFetchMessageList(CommonUser receiver);
}
