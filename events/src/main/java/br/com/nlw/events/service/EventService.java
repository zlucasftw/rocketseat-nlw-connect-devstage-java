package br.com.nlw.events.service;

import br.com.nlw.events.entity.EventEntity;
import br.com.nlw.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventEntity addNewEvent(EventEntity event) {
        // Pretty name of event is created from title
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
