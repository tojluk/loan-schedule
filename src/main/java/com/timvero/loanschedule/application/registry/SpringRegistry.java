package com.timvero.loanschedule.application.registry;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Интерфейс SpringRegistry используется для создания и доступа к реестру объектов по ключу.
 *
 * @param <K> Тип ключа, используемого для идентификации объектов в реестре
 * @param <V> Тип объектов, хранящихся в реестре
 */
public interface SpringRegistry<K, V> {

    /**
     * Создает карту (реестр) из списка элементов, используя предоставленную функцию для извлечения ключа.
     *
     * Метод принимает список объектов типа V (или его подтипов) и функцию,
     * которая преобразует каждый объект в ключ типа K.
     *
     * Работа метода:
     * 1. Преобразует список в поток (stream)
     * 2. Использует Collectors.toMap для создания Map, где:
     *    - keyExtractor применяется к каждому элементу для получения ключа
     *    - Function.identity() возвращает сам элемент как значение
     *
     * @param items Список элементов для добавления в реестр
     * @param keyExtractor Функция для извлечения ключа из элемента
     * @return Map, сопоставляющая ключи с соответствующими элементами
     */
    default Map<K, V> createRegistry(List<? extends V> items,
                                     Function<V, K> keyExtractor) {
        return items.stream()
                    .collect(Collectors.toMap(keyExtractor,
                                              Function.identity()
        ));
    }

    /**
     * Получает элемент из реестра по указанному ключу.
     *
     * @param key Ключ для поиска элемента
     * @return Элемент, соответствующий ключу
     */
    V get(K key);
}
