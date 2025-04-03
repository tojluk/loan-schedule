package com.timvero.loanschedule.service.registry;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public interface SpringRegistry<K, V> {

    default Map<K, V> createRegistry(List<? extends V> items,
                                     Function<V, K> keyExtractor) {
        return items.stream().collect(Collectors.toMap(
                keyExtractor,
                Function.identity()
        ));
    }

    V get(K key);
}
