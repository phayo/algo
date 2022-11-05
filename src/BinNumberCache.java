import java.util.*;

public class BinNumberCache {
    public static void main(String[] args) {
        BinRange r1 = new BinRange("400000000000", "419999999999", "visa");
        BinRange r2 = new BinRange("420008000000", "420089999999", "visadebit");
        BinRange r3 = new BinRange("435000000000", "435000999999", "visa");
        BinRange r4 = new BinRange("540008000000", "599999999999", "mc");

        CardTypeCache cache = buildCache(Arrays.asList(r2, r4, r3, r1));

        String e1 = "4111111111111111";
        String e2 = "4300999999991028122";
        String e3 = "43500199999901028122";
        String e4 = "5999999999991028122";

        System.out.println("Card type of " + e4 + " is: " + cache.get(e4));
    }

    static final class BinRange{
        final String start;
        final String end;
        final String cardType;
        Long card;

        BinRange(String start, String end, String cardType) {
            this.start = start;
            this.end = end;
            this.cardType = cardType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BinRange binRange = (BinRange) o;
            return Objects.equals(start, binRange.start) && Objects.equals(end, binRange.end) && Objects.equals(cardType, binRange.cardType) && Objects.equals(card, binRange.card);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, cardType, card);
        }
    }

    interface CardTypeCache{
        String get(String CardNumber);
    }

    static class CardTypeMapCache implements CardTypeCache{
        TreeMap<Long, String> rangeStartMap;
        Map<Long, Long> rangeEndMap;

        CardTypeMapCache(TreeMap<Long, String> startMap, Map<Long, Long> endMap){
            this.rangeEndMap = endMap;
            this.rangeStartMap = startMap;
        }

        @Override
        public String get(final String cardNumber) {
            String bin = cardNumber.substring(0, 12);
            Long card = Long.parseLong(bin);
            final Map.Entry<Long, String> floor = rangeStartMap.floorEntry(card);

            if(floor == null){
                return null;
            }

            return Optional.ofNullable(rangeEndMap.get(floor.getKey()))
                    .filter(end -> card <= end)
                    .map(end -> floor.getValue())
                    .orElse(null);
        }
    }

    public static CardTypeCache buildCache(List<BinRange> binRanges){
        TreeMap<Long, String> rangeStartMap = new TreeMap<>();
        HashMap<Long, Long> rangeEndMap = new HashMap<>();
        for(BinRange range: binRanges){
            Long x = Long.parseLong(range.start);
            Long y = Long.parseLong(range.end);
            rangeStartMap.put(x, range.cardType);
            rangeEndMap.put(x, y);
        }

        return new CardTypeMapCache(rangeStartMap, rangeEndMap);
    }
}
