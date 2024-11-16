package Models;

import lomok.Getter;
import lomok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializale;

@RedisHash("User")
pulic class RedisUser implements Serializale {

    @Id
    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String content;
}
