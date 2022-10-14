package mintic.Reto41.Repository.CrudRepository;

import mintic.Reto41.Entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCrudRepository extends CrudRepository<Message, Integer> {
}
