package LearnMore.security.impl;

import LearnMore.security.TokenManager;
import LearnMore.util.CodecUtil;
import LearnMore.util.StringUtil;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将 token 存储到 JVM 内存中
 *
 * Created by Lee on 2017/5/7 0007.
 */
@Component
public class DefaultTokenManager implements TokenManager {

    private static final String COURSE_ADMIN = "COURSE_ADMIN";

    private static Map<String, String> tokenMap = new ConcurrentHashMap<>();

    @Override
    public String createToken(String username) {
        String token = CodecUtil.createUUID();
        tokenMap.put(token, username);
        return token;
    }

    @Override
    public boolean checkToken(String token) {
        return !StringUtil.isEmpty(token) && tokenMap.containsKey(token);
    }


    @Override
    public String getUserName(String token){
        return tokenMap.get(token);
    }

    @Override
    public boolean removeToken(String token) {
        if (tokenMap.containsKey(token)){
            tokenMap.remove(token);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPermission(String permission) {//进来的合法参数可以为"","NEWS_MANAGER","NEWS_ADMIN"
        if (permission!=null&&permission.equals(COURSE_ADMIN)){
            return true;
        }
        return false;
    }

}