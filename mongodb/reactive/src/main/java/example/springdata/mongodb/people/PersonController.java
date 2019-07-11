package example.springdata.mongodb.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PersonController {
    private final ReactivePersonRepository repository;

    @Autowired
    public PersonController(final ReactivePersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping(name = "/persons", produces = {"text/event-stream"})
    public Flux<Person> getPersons() {
        return repository.findAll();
    }

    @PostMapping("/persons")
    public Mono<Person> createPerson(@RequestBody final Person person) {
        return repository.save(person);
    }
    

}