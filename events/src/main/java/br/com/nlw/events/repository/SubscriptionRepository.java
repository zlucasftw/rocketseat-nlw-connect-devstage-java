package br.com.nlw.events.repository;

import br.com.nlw.events.entity.EventEntity;
import br.com.nlw.events.entity.SubscriptionEntity;
import br.com.nlw.events.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Integer> {
    SubscriptionEntity findByEventAndSubscriber(EventEntity event, UserEntity user);
}
