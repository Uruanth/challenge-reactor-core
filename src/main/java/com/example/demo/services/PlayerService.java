package com.example.demo.services;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.stream.Collectors.groupingBy;


@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;


    private Flux<Player> getAll() {
        return playerRepository.findAll()
                .buffer(100)
                .flatMap(players -> Flux.fromStream(players.parallelStream()))
                .switchIfEmpty(Mono.error(new RuntimeException("Objeto vacio")));

    }

    private Flux<Player> mayores34() {
        return getAll()
                .buffer(100)
                .flatMap(juga -> Flux.fromStream(juga.parallelStream()))
                .filter(jugador -> {
                    try {
                        return jugador.getAge() > 34;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Objeto vacio Find all")));
    }

    private Flux<Player> clubEspecifico() {
        return mayores34()
                .buffer(100)
                .flatMap(juga -> Flux.fromStream(juga.parallelStream()))
                .filter(jugador -> {
                    try {
                        return jugador.getClub().equals("FC Barcelona");
                    } catch (Exception e) {
                        return false;
                    }
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Objeto vacio Filtro 1")));
    }


    public Flux<Player> getFilterPlayer() {
        return clubEspecifico()
                .buffer(100)
                .flatMap(juga -> Flux.fromStream(juga.parallelStream()))

                .switchIfEmpty(Mono.error(new RuntimeException("Objeto vacio Filtro 2")));
    }


    public Flux<Player> getRankingPlayer() {
        var result = getAll()
                .buffer(100)
                .flatMap(juga -> Flux.fromStream(juga.parallelStream()))
                .distinct()
                .groupBy(Player::getNational)
                .flatMap(grupo -> {
                    grupo
                            .flatMap(p -> {
                                return Flux.just(p);
                            })
                            ;

                });

        //.collect(groupingBy(Player::getNational))
        //.flatMap(nacionalList -> Mono.just(nacionalList));

        return null;
    }


}
