package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class OtherServiceImpl implements OtherService {
    public Optional<Integer> getIterableSequenceSum(Integer limitParam) {
        return Optional.of(IntStream // Unlike Stream, IntStream is a stream of primitive int values
                .iterate(1, a -> a + 1)
                .limit(limitParam)
                .reduce(0, Integer::sum));
    }
}
