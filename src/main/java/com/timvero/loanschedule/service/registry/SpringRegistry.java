package com.timvero.loanschedule.service.registry;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface SpringRegistry<T, E>{

    default Map<E, T> createRegistry(List<? extends T> items, Function<T, E> keyExtractor) {
        return items.stream().collect(Collectors.toMap(
                keyExtractor,
                Function.identity()
        ));
    }

    T get(E key);
}
