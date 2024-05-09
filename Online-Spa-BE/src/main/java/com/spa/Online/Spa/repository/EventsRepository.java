package com.spa.Online.Spa.repository;

import com.spa.Online.Spa.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventsRepository extends JpaRepository<Events, Long> {

    public List<Events> findEventsBySpaId(Long id);
}
