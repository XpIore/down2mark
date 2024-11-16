package two.down.bad.guys.down2mark.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisUserRepository extends CrudRepository<two.down.bad.guys.down2mark.Models.RedisUser, String> {
}
