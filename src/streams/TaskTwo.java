package streams;

import utils.StreamUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskTwo {
    public static void main(String[] args) {

        List<Stream<String>> dataset = new ArrayList<Stream<String>>() {{

            add(Stream.of("093 987 65 43", "(050)1234567", "12-345"));
            add(Stream.of("067-21-436-57", "050-2345678", "0939182736", "224-19-28"));
            add(Stream.of("(093)-11-22-334", "044 435-62-18", "721-73-45"));
        }};

        Map<String, Stream<String>> formattedDataset = StreamUtils.phoneNumbers.apply(dataset);

        formattedDataset.forEach((key, value) -> System.out.println(
                key + ": " + value.collect(Collectors.joining(" "))
        ));
    }
}
