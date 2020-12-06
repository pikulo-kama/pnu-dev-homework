package tests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import tests.utils.MapEntryImpl;
import utils.StreamUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class StreamUtilsTest {

    @Test
    public void nameListTest() {

        // Arrange
        Map<String, Stream<String>> dataset = new HashMap<String, Stream<String>>() {{

            put("Desktop", Stream.of(" iVan", "PeTro ", "Ira "));
            put("Web", Stream.of("STepan", "ira ", " Andriy ", "an na"));
            put("Spring", Stream.of("Ivan", "Anna"));
        }};

        List<String> expected = Arrays.asList("Andriy", "Anna", "Ira", "Ivan", "Petro", "Stepan");

        // Act
        List<String> actual = StreamUtils.nameList.apply(dataset)
                .sorted()
                .collect(Collectors.toList());

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void phoneNumbersTest() {
        // Arrange
        List<Stream<String>> dataset = new ArrayList<Stream<String>>() {{

            add(Stream.of("093 987 65 43", "(050)1234567", "12-345"));
            add(Stream.of("067-21-436-57", "050-2345678", "0939182736", "224-19-28"));
            add(Stream.of("(093)-11-22-334", "044 435-62-18", "721-73-45"));
        }};

        Map<String, List<String>> expected = new HashMap<String, List<String>>() {{
            put("050", Arrays.asList("1234567", "2345678"));
            put("067", Arrays.asList("2143657"));
            put("093", Arrays.asList("1122334", "9182736", "9876543"));
            put("044", Arrays.asList("4356218"));
            put("loc", Arrays.asList("2241928", "7217345"));
            put("err", Arrays.asList("12345"));
        }};

        // Act
        Map<String, List<String>> actual = StreamUtils.phoneNumbers.apply(dataset)
                .entrySet()
                .stream()
                .map(entry -> new MapEntryImpl<>(
                        entry.getKey(),
                        entry.getValue().collect(Collectors.toList())))
                .collect(Collectors.toMap(MapEntryImpl::getKey, MapEntryImpl::getValue));

        // Assert
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void sumEvenTest() {

        // Arrange
        Stream<IntStream> dataset = Stream.of(
                IntStream.of(-2, -4, 1, 8, 3, 10),
                IntStream.of(2, -4, 4, 0, 3, 1),
                IntStream.of(1, -4, 3, 5, 3, 1)
        );
        Integer expected = 10;

        // Act
        Integer actual = StreamUtils.sumEven.apply(dataset);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void countNumbersTest() {
        // Arrange
        Long expected = 7L;

        IntStream intStream = IntStream.of(3, 2, 1, 13, 21, 15);
        Stream<String> stringStream = Stream.of("9", "4", "23", "0", "32", "5");

        // Act
        Long actual = StreamUtils.countNumbers.apply(intStream, stringStream);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void duplicateElementsTest() {
        // Arrange
        Stream<Integer> dataset = Stream.of(3, 2, 1, 1, 12, 3, 8, 2, 4, 2);
        List<Integer> expected = Arrays.asList(1, 2, 3);

        // Act
        List<Integer> actual = StreamUtils.duplicateElements.apply(dataset)
                .collect(Collectors.toList());

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}