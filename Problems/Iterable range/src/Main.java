import java.util.Iterator;

class Range implements Iterable<Long> {

    private final long fromInclusive;
    private final long toExclusive;

    public Range(long from, long to) {
        this.fromInclusive = from;
        this.toExclusive = to;
    }

    @Override
    public Iterator<Long> iterator() {
        // write your code here
        return new Iterator<>() {

            private long current = fromInclusive;

            @Override
            public boolean hasNext() {
                return current < toExclusive;
            }

            @Override
            public Long next() {
                return current++;
            }
        };
    }

    public static void main(String[] args) {
        Range range = new Range(2, 6);
        range.forEach(System.out::println);
    }
}