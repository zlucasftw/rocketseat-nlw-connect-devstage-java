package br.com.nlw.events.repository;

import br.com.nlw.events.dto.SubscriptionRankingItemDTO;
import br.com.nlw.events.entity.EventEntity;
import br.com.nlw.events.entity.SubscriptionEntity;
import br.com.nlw.events.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Integer> {
    SubscriptionEntity findByEventAndSubscriber(EventEntity event, UserEntity user);

    @Query(value = "SELECT COUNT(subscription_number) AS Quantity, indication_user_id, user_name" +
            " FROM tbl_subscription" +
            " INNER JOIN tbl_user ON tbl_subscription.indication_user_id = tbl_user.user_id" +
            " WHERE indication_user_id IS NOT NULL AND :event_id = 9" +
            " GROUP BY indication_user_id" +
            " ORDER BY Quantity DESC", nativeQuery = true)
    List<SubscriptionRankingItemDTO> generateRanking(@Param(value = "event_id") Integer eventId);
}
