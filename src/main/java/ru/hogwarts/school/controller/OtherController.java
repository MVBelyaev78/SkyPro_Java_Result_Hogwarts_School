package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.OtherService;

import java.util.Optional;

@RestController
@RequestMapping("/other")
public class OtherController {

    private final OtherService otherService;

    public OtherController(OtherService otherService) {
        this.otherService = otherService;
    }

    @GetMapping("iterable_sequence_sum/{limitParam}")
    public ResponseEntity<Integer> findIterableSequenceSum(@PathVariable Integer limitParam) {
        return otherService
                .getIterableSequenceSum(limitParam)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
