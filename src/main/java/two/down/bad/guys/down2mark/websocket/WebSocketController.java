package two.down.bad.guys.down2mark.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import two.down.bad.guys.down2mark.markdown.RedisService;

@Controller
public class WebSocketController {

    private final RedisService redisService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketController(RedisService redisService, SimpMessagingTemplate simpMessagingTemplate) {
        this.redisService = redisService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/edit")
    public void handleMarkdownUpdate(@Header("token") String token, String content) {
        redisService.setContent(token, content);  // Use the token to identify context
        simpMessagingTemplate.convertAndSend("/topic/" + token, content);
    }

}



