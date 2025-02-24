package br.com.nlw.events.service;

import br.com.nlw.events.dto.SubscriptionResponseDTO;
import br.com.nlw.events.entity.EventEntity;
import br.com.nlw.events.entity.SubscriptionEntity;
import br.com.nlw.events.entity.UserEntity;
import br.com.nlw.events.exception.SubscriptionConflictException;
import br.com.nlw.events.exception.UserIndicatorNotFoundException;
import br.com.nlw.events.repository.EventRepository;
import br.com.nlw.events.repository.SubscriptionRepository;
import br.com.nlw.events.repository.UserRepository;
import br.com.nlw.events.exception.EventNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionResponseDTO createNewSubscription(String eventName, UserEntity user, Integer userId) {
        // Retrieve the event by its name
        EventEntity event = this.eventRepository.findByPrettyName(eventName);
        if (event == null) { // Alternative case 2
            throw new EventNotFoundException("The given event '" + eventName + "' couldn't be found.");
        }

        UserEntity retrievedUser = this.userRepository.findByEmail(user.getEmail());
        if (retrievedUser == null) { // Alternative case 1
            retrievedUser = this.userRepository.save(user);
        }

        UserEntity indicatorUser = this.userRepository.findById(userId).orElse(null);
        if (indicatorUser == null) {
            throw new UserIndicatorNotFoundException("Indicator user with following ID: " + userId + " it's not an existing user");
        }

        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setEvent(event);
        subscription.setSubscriber(retrievedUser);
        subscription.setIndication(indicatorUser);

        SubscriptionEntity duplicatedSubscription = this.subscriptionRepository.findByEventAndSubscriber(event, retrievedUser);
        if (duplicatedSubscription != null) { // Alternative case 3
            throw new SubscriptionConflictException(
                    "The following user " + user.getName() + " has already been subscribed at the event: " + event.getTitle());
        }

        // SubscriptionEntity addedSubscription = this.subscriptionRepository.save(subscription);
        // return addedSubscription;
        // return this.subscriptionRepository.save(subscription);
        this.subscriptionRepository.save(subscription);
        return new SubscriptionResponseDTO(subscription.getSubscriptionNumber(),
                "http://codecraft.com/subscription" + subscription.getEvent().getPrettyName() +
                        "/" + subscription.getSubscriber().getId());
    }

}
