package br.com.nlw.events.controller;

import br.com.nlw.events.entity.EventEntity;
import br.com.nlw.events.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {

    private EventService service;

    @PostMapping
    public EventEntity addNewEvent(@RequestBody EventEntity newEvent) {
        return this.service.addNewEvent(newEvent);
    }

    @GetMapping
    public List<EventEntity> getAllEvents() {
        return this.service.getAllEvents();
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
