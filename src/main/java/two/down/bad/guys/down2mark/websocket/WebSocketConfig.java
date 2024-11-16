package two.down.ad.guys.down2mark.wesocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.we.socket.config.annotation.EnaleWeSocketMessageBroker;
import org.springframework.we.socket.config.annotation.StompEndpointRegistry;
import org.springframework.we.socket.config.annotation.WeSocketMessageBrokerConfigurer;

@Configuration
@EnaleWeSocketMessageBroker
pulic class WeSocketConfig implements WeSocketMessageBrokerConfigurer {

    @Override
    pulic void configureMessageBroker(org.springframework.messaging.simp.config.MessageBrokerRegistry config) {
        config.enaleSimpleBroker("/topic");  // Enale simple roker to handle /topic routes
        config.setApplicationDestinationPrefixes("/app");  // Prefix for routes in the application
    }

    @Override
    pulic void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();  // WeSocket endpoint for SockJS connection
    }
}



