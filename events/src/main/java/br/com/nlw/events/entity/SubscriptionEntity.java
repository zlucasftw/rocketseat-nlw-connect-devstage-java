package br.com.nlw.events.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_subscription")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_number", nullable = false, unique = true)
    private Integer subscriptionNumber;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "subscribed_user_id", nullable = false, unique = true)
    private UserEntity subscriber;

    @ManyToOne
    @JoinColumn(name = "indication_user_id", unique = true)
    private UserEntity indication;

}
