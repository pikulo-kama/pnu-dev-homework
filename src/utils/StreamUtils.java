package utils;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {

    public static Function<Map<String, Stream<String>>, Stream<String>> distincter;
    public static Function<List<Stream<String>>, Map<String, Stream<String>>> phoneNumberAnalyzer;


    private static class NumberGroupResolver {

        public static String resolveKey(String number) {
            if (number.length() == 10)
                return number.substring(0, 3);

            if (number.length() == 7)
                return "loc";

            return "err";
        }

        public static Stream<String> resolveValue(String number) {
            if (number.length() == 10)
                return Stream.of(number.substring(3));

            return Stream.of(number);
        }

    }

    static {
        distincter = dataset -> dataset
                .values()
                .stream()
                .flatMap(Function.identity())
                .map(string -> string.replaceAll("\\s+", ""))
                .map(String::toLowerCase)
                .map(StringUtils::capitalize)
                .distinct();

        phoneNumberAnalyzer = dataset -> dataset.stream()
                .flatMap(Function.identity())
                .map(number -> number.replaceAll("[-\\s()]+", ""))
                .collect(Collectors.toMap(
                        NumberGroupResolver::resolveKey,
                        NumberGroupResolver::resolveValue,
                        (first, second) -> first
                ));
    }
}
