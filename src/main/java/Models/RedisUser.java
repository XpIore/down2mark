package Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("User")
public class RedisUser implements Serializable {

    @Id
    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private String content;
}
