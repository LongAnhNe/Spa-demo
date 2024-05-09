package com.spa.Online.Spa.service;

import com.spa.Online.Spa.exception.ProductException;
import com.spa.Online.Spa.model.Events;

import java.util.List;

public interface EventsService {

    public Events createEvent(Events event, Long restaurantId) throws ProductException;

    public List<Events> findAllEvent();

    public List<Events> findRestaurantsEvent(Long id);

    public void deleteEvent(Long id) throws Exception;

    public Events findById(Long id) throws Exception;
}
