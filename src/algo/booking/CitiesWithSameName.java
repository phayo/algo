package algo.booking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * Booking java streams test
 */
public class CitiesWithSameName {
    private record Data(int hotelId, String hotel, String city){}

    private static List<Data> data = List.of(
            new Data(1, "Fletcher hotel", "Rotterdam"),
            new Data(2, "Holiday Inn Express", "Rotterdam"),
            new Data(3, "Fletcher hotel", "London"),
            new Data(3, "Fletcher hotel", "Munich"),
            new Data(4, "Marriott", "Rotterdam"),
            new Data(5, "Holiday Inn Express", "Paris"),
            new Data(6, "Fletcher hotel", "Malaga"),
            new Data(7, "Spartan Hotel", "Athens"),
            new Data(7, "Spartan Hotel", "London"),
            new Data(8, "Queens Hotel", "London"),
            new Data(9, "Holiday Inn Express", "Barcelona"),
            new Data(10, "Marriott", "Paris"),
            new Data(11, "Marriott", "Munich"),
            new Data(12, "Nicon hotel", "Amsterdam"),
            new Data(13, "Hotel L'Paris", "Paris"),
            new Data(14, "Marriott", "Amsterdam"),
            new Data(15, "Nicon hotel", "Rotterdam"),
            new Data(16, "Marriott", "London"),
            new Data(17, "Marriott", "Copenhagen"),
            new Data(18, "Marriott", "Basel"),
            new Data(19, "Red light Hotel", "Amsterdam"),
            new Data(20, "Fletcher hotel", "Paris")
    );

    // TODO: get more context
    public static void main(String[] args) {
        // Group the data into hotels and all the cities they appear in
        final Map<String, List<String>> hotelsToCityMap = data.stream()
                .collect(Collectors.groupingBy(Data::hotel, mapping(Data::city, toList())));

        List<String> hotelsIn3orMoreCities = hotelsToCityMap.entrySet()
                .stream().filter(entry -> entry.getValue().size() >= 3)
                .map(Entry::getKey)
                .toList();

        // Stream the resulting map for all the cities and group by counting them
        Map<String, Long> occurrences = hotelsToCityMap.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), counting()));

        List<String> result = occurrences.entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= 3)
                .map(Entry::getKey)
                .peek(System.out::println)
                .toList();



        // =================== TESTING ONLY =====================
        System.out.println();
        final Map<String, List<String>> cityToHotelMap = data.stream()
                .collect(Collectors.groupingBy(Data::city, mapping(Data::hotel, toList())));

        result.forEach(
                city -> System.out.println(
                        "Hotels found in city '" + city + "' is:  " + String.join(" -> ", cityToHotelMap.getOrDefault(city, List.of()))
                )
        );
    }
}
