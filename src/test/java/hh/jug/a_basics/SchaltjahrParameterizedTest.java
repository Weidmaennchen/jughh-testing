package hh.jug.a_basics;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static hh.jug.a_basics.Schaltjahr.isSchaltjahr;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchaltjahrParameterizedTest {
    public static Stream<Arguments> multipleOf400() {
        return Stream.of(
                Arguments.of(Named.of("negativ", -400)),
                Arguments.of(Named.of("negativ high", -400000)),
                Arguments.of(Named.of("null", 0)),
                Arguments.of(Named.of("positiv", 400)),
                Arguments.of(Named.of("positiv high", 400000))
        );
    }

    @ParameterizedTest
    @MethodSource("multipleOf400")
    void dividableBy400IsSchaltjahr(int jahr) {
        assertTrue(isSchaltjahr(jahr));
    }


    @ParameterizedTest(name = "[{index}] {0}")
    @CsvSource({
            "negativ, -400",
            "negativ high, -400000",
            "null, 0",
            "positiv, 400",
            "positiv high, 400000"
    })
    void dividableBy400IsSchaltjahrCsv(String testname, int jahr) {
        assertTrue(isSchaltjahr(jahr));
    }
}
