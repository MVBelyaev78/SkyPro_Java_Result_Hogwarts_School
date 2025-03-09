package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class OtherServiceImpl implements OtherService {
    public Optional<Integer> getIterableSequenceSum(Integer limitParam) {
        // Unlike Stream, IntStream is a stream of primitive int values
        // rangeClosed(int startInclusive, int endInclusive) returns a sequential ordered IntStream from
        // startInclusive (inclusive) to endInclusive (inclusive) by an incremental step of 1
        return Optional.of(IntStream
                .rangeClosed(1, limitParam)
                .parallel()
                .sum());
    }
}
