package algo;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class AdyenPaymentDurationTest {
    public static void main(String[] args) {
        EventTracker tracker = new EventTracker();

        // Register some events with unsorted timestamps
        long currentTime = System.currentTimeMillis();
        tracker.registerEvent(new Event("event1", currentTime - Duration.ofHours(1).toMillis())); // 1 hour ago
        tracker.registerEvent(new Event("event1", currentTime - Duration.ofHours(3).toMillis())); // 3 hours ago
        tracker.registerEvent(new Event("event1", currentTime - Duration.ofMinutes(30).toMillis())); // 30 minutes ago
        tracker.registerEvent(new Event("event1", currentTime - Duration.ofHours(2).toMillis())); // 2 hours ago
        tracker.registerEvent(new Event("event1", currentTime - Duration.ofHours(2).toMillis())); // Duplicate timestamp

        // Query the number of occurrences in the last 2 hours
        int count = tracker.getNumberOfOccurrences("event1", Duration.ofHours(2));
        System.out.println("Number of occurrences in the last 2 hours: " + count); // Should print 3
    }
}

class Event {
    private String hash;
    private long timestamp;

    public Event(String hash, long timestamp) {
        this.hash = hash;
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

class EventTracker {
    private Map<String, TreeMap<Long, Integer>> eventMap;

    public EventTracker() {
        eventMap = new HashMap<>();
    }

    public void registerEvent(Event event) {
        String hash = event.getHash();
        long timestamp = event.getTimestamp();

        // Use TreeMap to store timestamps in sorted order
        TreeMap<Long, Integer> timestampMap = eventMap.computeIfAbsent(hash, k -> new TreeMap<>());

        // Increment the count for the timestamp (handles duplicates)
        timestampMap.put(timestamp, timestampMap.getOrDefault(timestamp, 0) + 1);
    }

    public int getNumberOfOccurrences(String eventHash, Duration duration) {
        TreeMap<Long, Integer> timestampMap = eventMap.get(eventHash);
        if (timestampMap == null) {
            return 0;
        }

        long currentTime = System.currentTimeMillis();
        long startTime = currentTime - duration.toMillis();

        // Use TreeMap's tailMap to get all timestamps >= startTime
        SortedMap<Long, Integer> relevantEvents = timestampMap.tailMap(startTime);

        // Sum the counts of all relevant events
        int count = 0;
        for (int eventCount : relevantEvents.values()) {
            count += eventCount;
        }

        return count;
    }
}
