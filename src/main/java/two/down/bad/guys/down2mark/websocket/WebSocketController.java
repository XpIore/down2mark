package two.down.bad.guys.down2mark.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import two.down.bad.guys.down2mark.markdown.MarkdownService;

@Controller
public class WebSocketController {

    private final MarkdownService markdownService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketController(MarkdownService markdownService, SimpMessagingTemplate simpMessagingTemplate) {
        this.markdownService = markdownService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/edit")
    public void handleMarkdownUpdate(@Header("token") String token, String content) {
        markdownService.setContent(token, content);  // Use the token to identify context
        simpMessagingTemplate.convertAndSend("/topic/" + token, content);
    }

}



