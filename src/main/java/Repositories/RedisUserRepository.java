package Repositories;

import Models.RedisUser;
import org.springframework.data.repository.CrudRepository;

public interface RedisUserRepository extends CrudRepository<RedisUser, String> {
}
