package streams;

import utils.StreamUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskFive {

    public static void main(String[] args) {
        Stream<Integer> dataset = Stream.of(3, 2, 1, 1, 12, 3, 8, 2, 4, 2);

        Stream<Integer> formattedDataset = StreamUtils.duplicateElements.apply(dataset);

        System.out.println("Result: " +
                formattedDataset
                        .map(Object::toString)
                        .collect(Collectors.joining(" "))
        );
    }


}
