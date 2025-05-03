package grades;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalyzeGradesTest {

    static Stream<TestCase> provideTestCases() {
        return Stream.of(
                new TestCase(new int[]{-5, 101, 49, 100, -1}, "Invalid grades detected."),   // Path 1
                new TestCase(new int[]{49, 49, 49, 49, 49}, "No students passed."),          // Path 2
                new TestCase(new int[]{55, 60, 65}, "All students passed."),                 // Path 3
                new TestCase(new int[]{}, "No students passed."),                            // Path 4
                new TestCase(new int[]{50, 45, 110}, "Invalid grades detected."),            // Path 5
                new TestCase(new int[]{50, 20, 0}, "Some students passed."),                 // Path 6
                new TestCase(new int[]{-1}, "Invalid grades detected."),                     // Path 7
                new TestCase(new int[]{30, 20, 10}, "No students passed."),                  // Path 8
                new TestCase(new int[]{55, 40, 75}, "Some students passed."),                // Path 9
                new TestCase(new int[]{49, 55}, "Some students passed."),                    // Path 10
                new TestCase(new int[]{70, 30}, "Some students passed."),                    // Path 11
                new TestCase(new int[]{55}, "All students passed."),                         // Path 12
                new TestCase(new int[]{50, 60, 40}, "Some students passed."),                // Path 13
                new TestCase(new int[]{51, 60, 90}, "All students passed."),                 // Path 14
                new TestCase(new int[]{0, 0, 0, 0, 0}, "No students passed."),               // Path 15
                new TestCase(new int[]{59, 49, 48}, "Some students passed."),                // Path 16
                new TestCase(new int[]{100, 40, 30}, "Some students passed."),               // Path 17
                new TestCase(new int[]{55, 49, 100}, "Some students passed.")                // Path 18
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testAnalyzeGrades(TestCase testCase) {
        assertEquals(testCase.expected, GradeAnalyzer.analyzeGrades(testCase.grades));
    }

    static class TestCase {
        int[] grades;
        String expected;

        TestCase(int[] grades, String expected) {
            this.grades = grades;
            this.expected = expected;
        }
    }
}
