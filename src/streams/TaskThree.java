package streams;

import utils.StreamUtils;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TaskThree {
    public static void main(String[] args) {

        IntStream s1 = IntStream.of(1,2 ,3,4,6);

        Stream<IntStream> dataset = Stream.of(
                IntStream.of(-2, -4, 1, 8, 3, 10),
                IntStream.of(2, -4, 4, 0, 3, 1),
                IntStream.of(1, -4, 3, 5, 3, 1)
        );

        System.out.println("Result: " + StreamUtils.sumEven.apply(dataset));
    }
}
