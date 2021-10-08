package com.alirismaurera.heroisapi.controller;


import com.alirismaurera.heroisapi.document.Heroes;
import com.alirismaurera.heroisapi.service.HeroesService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Data
@AllArgsConstructor
@RestController
public class HeroesController {
    private HeroesService heroesService;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    @GetMapping("/heroes")
    public Flux<Heroes> getAllItems(){
        log.info("requesting the list off all heroes");
        return heroesService.findAll();
    }

    @GetMapping("/heroes/{id}")
    public Mono<ResponseEntity<Heroes>> findByIdHero(@PathVariable String id){
        log.info("requesting the hero with id {}", id);
        return heroesService.findByIdHero(id).map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/heroes")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes){
        log.info("A new hero was created");
        return heroesService.save(heroes);
    }

    @DeleteMapping("/heroes/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteByIdHero(@PathVariable String id){
        log.info("Deleting a hero with id {}", id);
        heroesService.delete(id);
    }

    @PutMapping("/heroes/{id}")
    public Mono<Heroes> updateHero(@PathVariable String id, @RequestBody Heroes heroes){
        log.info("Hero update");
        return heroesService.updateHero(id, heroes);
    }


}
