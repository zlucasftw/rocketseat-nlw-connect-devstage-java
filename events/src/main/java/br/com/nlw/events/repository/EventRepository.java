package br.com.nlw.events.repository;

import br.com.nlw.events.entity.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Integer> {
    EventEntity findByPrettyName(String prettyName);
}
