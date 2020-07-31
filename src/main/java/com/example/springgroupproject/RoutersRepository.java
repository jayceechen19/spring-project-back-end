package com.example.springgroupproject;

import org.springframework.data.repository.CrudRepository;

public interface RoutersRepository extends CrudRepository<Router, Long> {
    Iterable<Router> findByActive(boolean active);
}
