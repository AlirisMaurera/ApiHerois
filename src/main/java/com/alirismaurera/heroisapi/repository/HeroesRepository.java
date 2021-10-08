package com.alirismaurera.heroisapi.repository;


import com.alirismaurera.heroisapi.document.Heroes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String> {
}
