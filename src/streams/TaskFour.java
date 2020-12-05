package streams;

import utils.StreamUtils;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TaskFour {
    public static void main(String[] args) {

        IntStream intStream = IntStream.of(3, 2, 1, 13, 21, 15);
        Stream<String> stringStream = Stream.of("9", "4", "23", "0", "32", "5");


        Long numbersCount = StreamUtils.countNumbers.apply(intStream, stringStream);

        System.out.println("Count of the numbers - " + numbersCount);
    }
}
