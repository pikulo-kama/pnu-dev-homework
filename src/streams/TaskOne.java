package streams;

import utils.StreamUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskOne {
    public static void main(String[] args) {

        Map<String, Stream<String>> dataset = new HashMap<String, Stream<String>>() {{

            put("Desktop", Stream.of(" iVan", "PeTro ", "Ira "));
            put("Web", Stream.of("STepan", "ira ", " Andriy ", "an na"));
            put("Spring", Stream.of("Ivan", "Anna"));
        }};

        Stream<String> formattedDataset = StreamUtils.nameList.apply(dataset);

        System.out.println("Result: " + formattedDataset.collect(Collectors.joining(" ")));

    }
}
