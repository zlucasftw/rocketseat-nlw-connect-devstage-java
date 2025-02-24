package br.com.nlw.events.controller;

import br.com.nlw.events.dto.ErrorMessageDTO;
import br.com.nlw.events.dto.SubscriptionResponseDTO;
import br.com.nlw.events.entity.SubscriptionEntity;
import br.com.nlw.events.entity.UserEntity;
import br.com.nlw.events.exception.SubscriptionConflictException;
import br.com.nlw.events.exception.UserIndicatorNotFoundException;
import br.com.nlw.events.service.SubscriptionService;
import br.com.nlw.events.exception.EventNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    private final SubscriptionService service;

    @PostMapping({"/{prettyName}", "/{prettyName}/{userId}"})
    public ResponseEntity<?> createSubscription(
            @PathVariable String prettyName,
            @RequestBody UserEntity subscriber,
            @PathVariable(required = false) Integer userId) {

        try {
            SubscriptionResponseDTO newSubscription = this.service.createNewSubscription(prettyName, subscriber, userId);
            if (newSubscription != null) {
                return ResponseEntity.created(URI.create("/subscription")).body(newSubscription);
            }
        } catch (EventNotFoundException | UserIndicatorNotFoundException exception) {
            return ResponseEntity.status(404).body(new ErrorMessageDTO(exception.getMessage()));
        } catch (SubscriptionConflictException exception) {
            return ResponseEntity.status(409).body(new ErrorMessageDTO(exception.getMessage()));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{prettyName}/ranking")
    public ResponseEntity<?> generateRakingByEvent(@PathVariable String prettyName) {
        try {
            return ResponseEntity.ok(this.service.getCompleteRanking(prettyName));
        } catch (EventNotFoundException exception) {
            return ResponseEntity.status(404).body(new ErrorMessageDTO(exception.getMessage()));
        }
    }

    @GetMapping("/{prettyName}/ranking/{userId}")
    public ResponseEntity<?> generateRankingByEventAndUser(@PathVariable String prettyName,
                                                           @PathVariable Integer userId) {

        try {
            return ResponseEntity.ok(this.service.getRankingByUser(prettyName, userId));
        } catch (Exception exception) {
            return ResponseEntity.status(404).body(new ErrorMessageDTO(exception.getMessage()));
        }
    }

}
