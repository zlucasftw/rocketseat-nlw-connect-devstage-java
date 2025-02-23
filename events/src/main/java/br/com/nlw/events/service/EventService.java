package br.com.nlw.events.service;

import br.com.nlw.events.entity.EventEntity;
import br.com.nlw.events.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    // EventRepository dependency injection
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventEntity addNewEvent(EventEntity event) {
        // Gerando o pretty name
        event.setPrettyName(event.getTitle().toLowerCase().replace(" ", "-"));
        return this.eventRepository.save(event);
    }

    public List<EventEntity> getAllEvents() {
        return (List<EventEntity>) this.eventRepository.findAll();
    }

    public EventEntity getByPrettyName(String prettyName) {
        return this.eventRepository.findByPrettyName(prettyName);
    }

}
