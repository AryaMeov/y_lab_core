package com.y.lab.hw2.person.task;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/*
   Task1
       Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

       Что должно получиться Key: Amelia
           Value:4
           Key: Emily
           Value:1
           Key: Harry
           Value:3
           Key: Jack
           Value:1
    */
public class Application {

    @EqualsAndHashCode
    @Getter
    @Setter
    @RequiredArgsConstructor
    static class Person {
        final int id;

        final String name;
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();
        test();


        var gropedPersons = filterAndSort(RAW_DATA);

        for (var entry : gropedPersons.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue().size());
            for (var person : entry.getValue()) {
                System.out.println(person.id + " - " + person.name);
            }
        }
    }

    private static Map<String, List<Person>> filterAndSort(Person[] arr) {
        return Arrays.stream(arr)
                .distinct()
                .sorted(Comparator.comparing(Person::getId))
                .collect(groupingBy(Person::getName));
    }

    private static void test() {
        var expected = new HashMap<String, List<Person>>() {{
            put("Amelia", List.of(new Person(5, "Amelia"), new Person(6, "Amelia"), new Person(7, "Amelia"), new Person(8, "Amelia")));
            put("Emily", List.of(new Person(3, "Emily")));
            put("Harry", List.of(new Person(0, "Harry"), new Person(1, "Harry"), new Person(2, "Harry")));
            put("Jack", List.of(new Person(4, "Jack")));
        }};
        var gropedPersons = filterAndSort(RAW_DATA);
        for (var entry : gropedPersons.entrySet()) {
            assert expected.get(entry.getKey()).size() == entry.getValue().size();
            for (int i = 0; i < entry.getValue().size(); i++) {
                assert expected.get(entry.getKey()).get(i).id == entry.getValue().get(i).id;
                assert expected.get(entry.getKey()).get(i).name.equals(entry.getValue().get(i).name);
            }
        }
    }
}
