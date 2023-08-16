package algo.booking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Booking java streams test. Return cities that has more than 3 hotels of the same name
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
            new Data(20, "Fletcher hotel", "Paris"),
            new Data(21, "Holiday Inn Express", "London"),
            new Data(9, "Casa L'Hotel", "Barcelona"),
            new Data(9, "Abuela Hotels", "Barcelona")
    );

    private static List<Data> data1 = List.of(
            new Data(1, "Fletcher hotel", "Rotterdam"),
            new Data(2, "Holiday Inn Express", "Rotterdam"),
            new Data(2, "Holiday Inn Express", "Rotterdam"),
            new Data(2, "Holiday Inn Express", "Rotterdam"),
            new Data(2, "Holiday Inn Express", "Rotterdam"),
            new Data(2, "Holiday Inn Express", "Rotterdam"),
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
            new Data(20, "Fletcher hotel", "Paris"),
            new Data(21, "Holiday Inn Express", "London"),
            new Data(9, "Casa L'Hotel", "Barcelona"),
            new Data(9, "Abuela Hotels", "Barcelona")
    );

    //Locations
    private static final String PARIS = "paris";
    private static final String LONDON = "london";
    private static final String ROTTERDAM = "rotterdam";

    //Hotel Names
    private static final String NH = "nh hotels";
    private static final String SAHEL = "sahel";
    private static final String MAHRIOT = "mahriot";
    private static final String DAN_HOT = "danhot";

    private record Hotel(String name, String city, int id) {
        public static Hotel from(String name, String city, int id) {
            return new Hotel(name, city, id);
        }
    }

    private static final List<Hotel> hotelList = List.of(

            Hotel.from(NH, ROTTERDAM, 1),
            Hotel.from(MAHRIOT, ROTTERDAM, 2),
            Hotel.from(SAHEL, ROTTERDAM, 3),
            Hotel.from(DAN_HOT, ROTTERDAM, 4),

            Hotel.from(NH, LONDON, 1),
            Hotel.from(MAHRIOT, LONDON, 2),
            Hotel.from(SAHEL, LONDON, 3),

            Hotel.from(MAHRIOT, PARIS, 1),
            Hotel.from(SAHEL, PARIS, 2),
            Hotel.from(DAN_HOT, PARIS, 3),
            Hotel.from(NH, PARIS, 4)
    );

    public static void main2(String[] args) {

        //1. Group hotels by their cities
        final Map<String, List<Hotel>> cityToHotelsMap = hotelList.stream().collect(Collectors.groupingBy(h -> h.city));

        //2. Defined a container, that I'll use to hold hotel names (list) to city name
        final Map<Set<String>, String> hotelNamesToCityNameMap = new HashMap<>();

        //3. Define a container that I'll use to hold the result
        final Set<String> result = new HashSet<>();

        //4. Loop through each of the city in the 'cityToHotelsMap'
        for (String city: cityToHotelsMap.keySet()) {

            //a. Get the hotels in that city and hotel names in that city
            List<Hotel> hotelsForCity = cityToHotelsMap.get(city);
            Set<String> hotelNameSet = hotelsForCity.stream().map(h -> h.name).collect(Collectors.toUnmodifiableSet());

            //b. If the hotels in that city is bigger than 2, then it is a candidate for consideration
            if (hotelsForCity.size() > 2) {

                //c. If our container map 'hotelNamesToCityNameMap' is not empty,
                if (!hotelNamesToCityNameMap.isEmpty()) {

                    //d. Check the map, if there are entries (keys) that have hotel names matching the names we have in 'a'
                    hotelNamesToCityNameMap
                            .entrySet()
                            .stream()
                            .filter(e -> {
                                final Set<String> existing = e.getKey();

                                //This is where we do the check, if incoming list is bigger, then the contains statement goes the other way around
                                if (existing.size() > hotelNameSet.size())
                                    return existing.containsAll(hotelNameSet);
                                else return hotelNameSet.containsAll(existing);
                            })
                            .forEach(e -> {
                                //e. If we find the match we want, we can now put the city name in the result container
                                result.add(city);
                                result.add(e.getValue());
                            });
                }

                //f. For cities with hotels bigger than 2, we store its hotel names inside this map along with the city name
                hotelNamesToCityNameMap.put(hotelNameSet, city);
            }
        }

        //Print out the result
        System.out.println(String.join(", ", result));
    }

    // TODO: get more context
    public static void main1(String[] args) {
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
    public static void main(String[] args) {
        getCitiesHaving3OrMoreHotelsOfSameNameInTheSameCity(data1);
    }

    private static  Set<String> getCitiesHaving3OrMoreHotelsOfSameNameInTheSameCity(List<Data> data){
        // Group the data into cities and all the hotels they appear in
        final Map<String, List<String>> cityToHotelsMap = data.stream()
                .collect(Collectors.groupingBy(Data::city, mapping(Data::hotel, toList())));

        Set<String> result = new HashSet<>();

        cityToHotelsMap
                .forEach((city, hotels) -> {
                    Map<String, Long> hotelOccurrences = hotels.stream().collect(Collectors.groupingBy(Function.identity(), counting()));

                    if(hotelOccurrences.values().stream().anyMatch(c -> c >= 3)){
                        System.out.println(city);
                        result.add(city);
                    }
                });

        return result;
    }

    private static Set<String> getCitiesWith3orMoreHotelsOfSameNameWithOtherCities(List<Data> data){
        // Group the data into cities and all the hotels they appear in
        final Map<String, Set<String>> cityToHotelsMap = data.stream()
                .collect(Collectors.groupingBy(Data::city, mapping(Data::hotel, toSet())));

        // get a set of all the hotels
        Set<String> hotels = data.stream().map(Data::hotel).collect(toSet());

        // Initialize a map to hold cities and the number of times they appeared in hotels that are present in 3 or more cities
        Map<String, Integer> cityCountMap =  new HashMap<>();

        int countOfCities = cityToHotelsMap.entrySet().size(); // get the number of unique cities basically

        // For each unique hotel
        for(String hotel: hotels){
            int count = 0; // initialize a counter

            Set<String> cities = new HashSet<>(); // set of all the cities with more than 3 hotels that we see for this hotel

            // For each city->hotel set
            for(Entry<String, Set<String>> cityToHotels: cityToHotelsMap.entrySet()){
                ++count; // increment counter

                // only if the city has more than 2 hotels, proceed
                if(cityToHotels.getValue().size() > 2){
                    // check that the current city has the sub loop has the hotel in the outer loop
                    if(cityToHotels.getValue().contains(hotel)) {
                        // if it does we add it to our seen cities
                        cities.add(cityToHotels.getKey());
                    }

                    // check if we have exhausted checking the occurrences of the hotel in the outer loop in all the unique cities
                    // also make sure the cities we find is more than 2, basically, meaning that the hotel occurred in more than 2 cities that has more than 2 hotels
                    if(count == countOfCities && cities.size() > 2){
                        // if the immediate above check passes, we add all the seen hotels to our cityCountMap and increment by 1, starting from zero if its the first occurrence
                        cities.stream()
                                .peek(city -> cityCountMap.computeIfAbsent(city, seen -> 0))
                                .forEach(city -> cityCountMap.computeIfPresent(city, (seen, val) -> ++val));
                        break;
                    }
                }

            }
        }

        // finally after our loop is done, we filter out seen cities from our map that did not occur up to 3 times. basically leaving us with only cities having more than 3 hotels
        // and at least 3 of those hotels occurred in other cities with 3 or more hotels.
        cityCountMap.entrySet().stream().filter(entry -> entry.getValue() >= 3)
                .forEach(city -> System.out.println("city: -> " + city.getKey() + " has " + city.getValue() + " hotels of the same name with other cities"));

        return cityCountMap.entrySet().stream().filter(entry -> entry.getValue() >= 3)
                .map(Entry::getKey)
                .collect(toSet());
    }
}
