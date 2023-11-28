import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500) 
  public void testMergeLeftEnd() {
    List<String> left = Arrays.asList("c", "d", "a");
    List<String> right = Arrays.asList("a", "b", "a");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testFilter1() {
    List<String> input = Arrays.asList("Moon", "moon", "Sun", "sun");
    List<String> filtered = ListExamples.filter(input, new IsMoon());
    List<String> expected = Arrays.asList("Moon", "moon");
    assertEquals(expected, filtered);
  }

  @Test(timeout = 500)
  public void testFilter2() {
    List<String> input = Arrays.asList("Moon", "moon", "Sun", "sun");
    List<String> filtered = ListExamples.filter(input, new StringChecker() {
      public boolean checkString(String s) {
        return s.equalsIgnoreCase("sun");
      }
    });
    List<String> expected = Arrays.asList("Sun", "sun");
    assertEquals(expected, filtered);
  }

  // Add a test to test filter, but call filter twice in a row with two different inputs to see if both are correct
  @Test(timeout = 500)
  public void testFilter3() {
    List<String> input = Arrays.asList("Moon", "moon", "Sun", "sun");
    List<String> filtered = ListExamples.filter(input, new StringChecker() {
      public boolean checkString(String s) {
        return s.equalsIgnoreCase("sun");
      }
    });
    List<String> expected = Arrays.asList("Sun", "sun");
    
    List<String> input2 = Arrays.asList("Moon", "moon", "Sun", "sun");
    List<String> filtered2 = ListExamples.filter(input2, new StringChecker() {
      public boolean checkString(String s) {
        return s.equalsIgnoreCase("moon");
      }
    });
    List<String> expected2 = Arrays.asList("Moon", "moon");

    assertEquals(expected, filtered);
    assertEquals(expected2, filtered2);
  }

  // Test with empty list
  @Test(timeout = 500)
  public void testFilter4() {
    List<String> input = Arrays.asList();
    List<String> filtered = ListExamples.filter(input, new StringChecker() {
      public boolean checkString(String s) {
        return s.equalsIgnoreCase("sun");
      }
    });
    List<String> expected = Arrays.asList();
    assertEquals(expected, filtered);
  }

  // Test with empty list followed by non-empty list, and see that both are correct
  @Test(timeout = 500)
  public void testFilter5() {
    List<String> input = Arrays.asList();
    List<String> filtered = ListExamples.filter(input, new StringChecker() {
      public boolean checkString(String s) {
        return s.equalsIgnoreCase("sun");
      }
    });
    List<String> expected = Arrays.asList();

    List<String> input2 = Arrays.asList("Moon", "moon", "Sun", "sun");
    List<String> filtered2 = ListExamples.filter(input2, new StringChecker() {
      public boolean checkString(String s) {
        return s.equalsIgnoreCase("moon");
      }
    });
    List<String> expected2 = Arrays.asList("Moon", "moon");

    assertEquals(expected, filtered);
    assertEquals(expected2, filtered2);
  }

  // Test merged with empty lists, and with one empty and one non-empty list
  @Test(timeout = 500)
  public void testMergeEmpty() {
    List<String> left = Arrays.asList();
    List<String> right = Arrays.asList();
    List<String> merged = ListExamples.merge(left, right);

    List<String> left2 = Arrays.asList();
    List<String> right2 = Arrays.asList("a", "b", "c");
    List<String> merged2 = ListExamples.merge(left2, right2);

    List<String> expected = Arrays.asList();
    List<String> expected2 = Arrays.asList("a", "b", "c");

    assertEquals(expected, merged);
    assertEquals(expected2, merged2);
  }

  // Test filter with some other string checkers, maybe try an empty string checker
  @Test(timeout = 500)
  public void testFilter6() {
    List<String> input = Arrays.asList("Moon", "moon", "Sun", "sun");
    List<String> filtered = ListExamples.filter(input, new StringChecker() {
      public boolean checkString(String s) {
        return s.equalsIgnoreCase("");
      }
    });
    List<String> expected = Arrays.asList();
    assertEquals(expected, filtered);
  }

  // Test filter with some other string checkers
  @Test(timeout = 500)
  public void testFilter7() {
    List<String> input = Arrays.asList("M", "A", "D", "C");
    List<String> filtered = ListExamples.filter(input, new StringChecker() {
      public boolean checkString(String s) {
        return s.equalsIgnoreCase("A");
      }
    });
    List<String> expected = Arrays.asList("Moon", "moon");
    assertEquals(expected, filtered);
  }

}
