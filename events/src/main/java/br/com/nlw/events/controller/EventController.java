package br.com.nlw.events.controller;



import br.com.nlw.events.entity.EventEntity;
import br.com.nlw.events.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService service;

    @PostMapping
    public ResponseEntity<EventEntity> addNewEvent(@RequestBody EventEntity newEvent) {
        var addedEvent = this.service.addNewEvent(newEvent);
        if (addedEvent != null) {
            return ResponseEntity.created(URI.create("/events")).body(addedEvent);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<EventEntity>> getAllEvents() {
        var allEvents = this.service.getAllEvents();
        if (!allEvents.isEmpty()) {
            return ResponseEntity.ok(allEvents);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{prettyName}")
    public ResponseEntity<EventEntity> getEventByPrettyName(@PathVariable String prettyName) {
        EventEntity event = this.service.getByPrettyName(prettyName);
        if (event != null) {
            return ResponseEntity.ok().body(event);
        }
        return ResponseEntity.notFound().build();
    }

}
