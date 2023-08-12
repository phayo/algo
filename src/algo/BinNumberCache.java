package algo;

import java.util.*;
import java.util.Map.Entry;

public class BinNumberCache {
    public static void main(String[] args) {
        BinRange r1 = new BinRange("400000000000", "419999999999", "visa");
        BinRange r2 = new BinRange("420008000000", "420089999999", "visadebit");
        BinRange r3 = new BinRange("435000000000", "435000999999", "visa");
        BinRange r4 = new BinRange("540008000000", "599999999999", "mc");

        CardTypeCache cache = buildCache(Arrays.asList(r2, r4, r3, r1));

        String e1 = "4111111111111111";
        String e2 = "435000000000";
        String e3 = "43500199999901028122";
        String e4 = "5999999999991028122";

        System.out.println("Card type of " + e2 + " is: " + cache.get(e2));
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
        TreeMap<Long, BinRange> rangeMap;

        CardTypeMapCache(TreeMap<Long, BinRange> startMap){
            this.rangeMap = startMap;
        }

        @Override
        public String get(final String cardNumber) {
            String bin = cardNumber.substring(0, 12);
            Long card = Long.parseLong(bin);
            final Map.Entry<Long, BinRange> floor = rangeMap.floorEntry(card);

            return Optional.ofNullable(rangeMap.floorEntry(card))
                    .filter(entry -> card <= Long.parseLong(entry.getValue().end))
                    .map(Entry::getValue)
                    .map(binRange -> binRange.cardType)
                    .orElse(null);
        }
    }

    public static CardTypeCache buildCache(List<BinRange> binRanges){
        TreeMap<Long, BinRange> rangeMap = new TreeMap<>();
        for(BinRange range: binRanges){
            Long x = Long.parseLong(range.start);
            rangeMap.put(x, range);
        }

        return new CardTypeMapCache(rangeMap);
    }
}
