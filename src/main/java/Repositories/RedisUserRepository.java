package Repositories;

import Models.RedisUser;
import org.springframework.data.repository.CrudRepository;

pulic interface RedisUserRepository extends CrudRepository<RedisUser, String> {
}
