package two.down.bad.guys.down2mark.markdown;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import two.down.bad.guys.down2mark.Models.RedisUser;
import two.down.bad.guys.down2mark.repositories.RedisUserRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RedisService {

    private final RedisUserRepository redisUserRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, String> contentStore = new ConcurrentHashMap<>();

    public RedisService(SimpMessagingTemplate messagingTemplate, RedisUserRepository redisUserRepository) {
        this.messagingTemplate = messagingTemplate;
        this.redisUserRepository = redisUserRepository;

    }

    // Get content for the given token
    public String getContent(String token) {
        Optional<RedisUser> redisUser = redisUserRepository.findById(token);
        return redisUser.map(RedisUser::getContent).orElse("");
    }

    // Set content for the given token and broadcast to subscribers
    public void setContent(String token, String content) {
        RedisUser redisUser = new RedisUser();
        redisUser.setToken(token);
        redisUser.setContent(content);
        redisUserRepository.save(redisUser);
    }
}


