package com.example.springgroupproject;

import org.springframework.data.repository.CrudRepository;

public interface SwitchRepository extends CrudRepository<Switch, Long> {
    Iterable<Switch> findByActive(boolean active);
}
