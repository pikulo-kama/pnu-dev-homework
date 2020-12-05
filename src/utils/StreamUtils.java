package utils;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class StreamUtils {

    public static Function<Map<String, Stream<String>>, Stream<String>> nameList;
    public static Function<List<Stream<String>>, Map<String, Stream<String>>> phoneNumbers;
    public static Function<Stream<IntStream>, Integer> sumEven;
    public static BiFunction<IntStream, Stream<String>, Long> countNumbers;
    public static Function<Stream<Integer>, Stream<Integer>> duplicateElements;

    private static class NumberGroupResolver {

        public static String resolveKey(String number) {
            if (number.length() == 10)
                return number.substring(0, 3);

            if (number.length() == 7)
                return "loc";

            return "err";
        }

        public static String resolveValue(String number) {
            if (number.length() == 10)
                return number.substring(3);

            return number;
        }

    }

    static {
        nameList = dataset -> dataset
                .values()
                .stream()
                .flatMap(Function.identity())
                .map(string -> string.replaceAll("\\s+", ""))
                .map(String::toLowerCase)
                .map(StringUtils::capitalize)
                .distinct();

        phoneNumbers = dataset -> dataset.stream()
                .filter(Objects::nonNull)
                .flatMap(Function.identity())
                .map(number -> number.replaceAll("[-\\s()]+", ""))
                .sorted()
                .collect(Collectors.groupingBy(
                        NumberGroupResolver::resolveKey,
                        Collectors.mapping(
                                NumberGroupResolver::resolveValue,
                                Collectors.toList()
                        )
                )).entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        number -> number.getValue().stream()
                ));


        sumEven = dataset -> dataset
                .filter(Objects::nonNull)
                .mapToInt(stream -> stream
                        .filter(number -> number % 2 == 0 && number > 0)
                        .max()
                        .orElse(0)
                )
                .sum();

        countNumbers = (intStream, stringStream) ->
                Stream.concat(
                        intStream.mapToObj(String::valueOf),
                        stringStream

                ).filter(
                        number -> !number.matches("0") && number.matches("\\d+") &&

                                (number.contains("3") || Integer.parseInt(number) % 3 == 0))
                .count();

        duplicateElements = dataset -> dataset
                .collect(Collectors.groupingBy(number -> number))
                .entrySet()
                .stream()
                .filter(pair -> pair.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .sorted();

    }
}
