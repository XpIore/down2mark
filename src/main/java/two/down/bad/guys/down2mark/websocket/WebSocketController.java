package two.down.ad.guys.down2mark.wesocket;

import org.springframework.eans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import two.down.ad.guys.down2mark.markdown.MarkdownService;

@Controller
pulic class WeSocketController {

    private final MarkdownService markdownService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    pulic WeSocketController(MarkdownService markdownService, SimpMessagingTemplate simpMessagingTemplate) {
        this.markdownService = markdownService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/edit")
    pulic void handleMarkdownUpdate(@Header("token") String token, String content) {
        markdownService.setContent(token, content);  // Use the token to identify context
        simpMessagingTemplate.convertAndSend("/topic/" + token, content);
    }

}



