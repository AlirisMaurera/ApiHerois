package com.alirismaurera.heroisapi.service;


import com.alirismaurera.heroisapi.document.Heroes;
import com.alirismaurera.heroisapi.repository.HeroesRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Data
@AllArgsConstructor
@Service
public class HeroesService {

    private final HeroesRepository heroesRepository;

    public Flux<Heroes> findAll(){
        return Flux.fromIterable(heroesRepository.findAll());
    }

    public Mono<Heroes> findByIdHero(String id){

        return Mono.justOrEmpty(heroesRepository.findById(id));
    }

    public Mono<Heroes> updateHero(String id, Heroes heroes){

        return Mono.justOrEmpty(heroesRepository.save(heroes));
    }

    public Mono<Heroes> save(Heroes heroes){
        return Mono.justOrEmpty(heroesRepository.save(heroes));
    }

    public void delete(String id){

        heroesRepository.deleteById(id);
    }

}
