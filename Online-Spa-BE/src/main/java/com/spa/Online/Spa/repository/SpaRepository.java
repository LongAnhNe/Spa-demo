package com.spa.Online.Spa.repository;

import com.spa.Online.Spa.model.Spa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpaRepository extends JpaRepository<Spa,Long> {

}
