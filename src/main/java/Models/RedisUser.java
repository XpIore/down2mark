package Models;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("User")
public class RedisUser implements Serializable {

}
