package algo.booking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class AggregateAvailableHotelsOnCheckIn {
    /*
    * If  a list of available hotel rooms for each given day is known. Return an aggregated list of options that meets all requirements provided as input. Requirements tally
    * with features of a room. A room has a price, noOfAvailability, features.
    * */

    public record Room(int price, int availability, Set<String> features){}
    public record RoomInput(int checkIn, int checkOut, int size, Set<String> features){}

    public List<Room> getAggregatedOptions(TreeMap<Integer, List<Room>> data, RoomInput roomInput){
        // Do we need to validate for checkIn and checkOut out of range?
        SortedMap<Integer, List<Room>> stayDays = data.subMap(roomInput.checkIn(), roomInput.checkOut());

        // Predicate to validate that a single room meets roomInput requirements
        Predicate<Room> matchCheck = room -> {
            if (room.availability < roomInput.size()){
                return false;
            }

            return room.features.containsAll(roomInput.features);
        };

        // Function that removes all rooms that are not meeting roomInput requirements from a list of rooms
        Function<List<Room>, List<Room>> mapValid = rooms -> rooms.stream().filter(matchCheck).toList();

        // Operation that returns a list of collection of all matched rooms for each stay day
        List<List<Room>> matchRoomsInStay = stayDays.values().stream().map(mapValid).toList();

        // Accumulate all the matching rooms into the result format
        List<Room> result = new ArrayList<>();
        for (int i = 0; i < matchRoomsInStay.get(0).size(); i++){
            dfs_AccumulateRooms(1, matchRoomsInStay.get(0).get(i), matchRoomsInStay, result);
        }

        return result;
    }

    private void dfs_AccumulateRooms(int stay, Room accumulated, List<List<Room>> matchRoomsInStay, List<Room> result){
        if (stay == matchRoomsInStay.size()){
            result.add(accumulated);
            return;
        }

        List<Room> roomsInStay = matchRoomsInStay.get(stay);
        for (Room room : roomsInStay) {
            int accumulatedPrice = accumulated.price + room.price;
            int minAvailability = Math.min(accumulated.availability, room.availability);

            Set<String> commonFeatures = new HashSet<>(accumulated.features);
            commonFeatures.retainAll(room.features);
            Room accumulate = new Room(accumulatedPrice, minAvailability, commonFeatures);

            dfs_AccumulateRooms(stay + 1, accumulate, matchRoomsInStay, result);
        }
    }


    public static void main(String[] args) {
        TreeMap<Integer, List<Room>> input = new TreeMap<>();
        input.put(176, List.of(new Room(120, 5,  Set.of("breakfast", "refundable"))));

        input.put(177, List.of(new Room(130, 1, Set.of("breakfast")),
                new Room(140, 3, Set.of("breakfast", "refundable", "wifi"))));

        input.put(178, List.of(new Room(130, 2, Set.of("breakfast")),
                new Room(140, 10, Set.of("breakfast", "refundable", "wifi"))));

        List<Room> result = new AggregateAvailableHotelsOnCheckIn().getAggregatedOptions(input, new RoomInput(176, 178, 1, Set.of("breakfast")));

        System.out.println("[");
        for (Room room : result) {
            System.out.print("{\n\tprice: " + room.price + ",\n\tavailability: " + room.availability + ",\n\tfeatures = [");
            for (String s : room.features) {
                System.out.print(s + ", ");
            }
            System.out.println("]\n\n},");
        }
        System.out.println("]");
    }
}
