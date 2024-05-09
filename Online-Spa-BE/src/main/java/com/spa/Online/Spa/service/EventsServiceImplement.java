package com.spa.Online.Spa.service;

import com.spa.Online.Spa.exception.ProductException;
import com.spa.Online.Spa.model.Events;
import com.spa.Online.Spa.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventsServiceImplement  implements EventsService{
    @Autowired
    private EventsRepository eventRepository;
    @Override
    public Events createEvent(Events event, Long restaurantId) throws ProductException {

        return null;
    }

    @Override
    public List<Events> findAllEvent() {
        return null;
    }

    @Override
    public List<Events> findRestaurantsEvent(Long id) {
        return null;
    }

    @Override
    public void deleteEvent(Long id) throws Exception {

    }

    @Override
    public Events findById(Long id) throws Exception {
        return null;
    }
}
