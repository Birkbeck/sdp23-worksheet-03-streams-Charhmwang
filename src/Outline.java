import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Outline {

  public static List<String> getList() {
    return List.of("hi", "bat", "ear", "hello", "iguana",
            "beaver", "winterland", "elephant", "eye", "qi");
  }

  // Loop through the words and print each one on a separate line,
  // with two spaces in front of each word.
  public static void question1() {
    List<String> words = getList();
    System.out.println("1: ");
    Stream<String> strings = words.stream();
    strings.forEach(s -> System.out.println("  " + s));
  }

  // Repeat this problem but without two spaces in front of each word.
  // This should be trivial if you use the same approach as the previous
  // question; the point here is to make use of a method reference.
  public static void question2() {
    List<String> words = getList();
    System.out.println("2: ");
    words.stream().forEach(System.out::println);
  }

  // For each of the following lambda expressions (see Question 5 in Worksheet 2),
  // produce the list that contains the elements of the original list
  // that satisfy the predicate defined by the lambda expression
  // (use the filter stream operation):
  //  - s -> s.length() < 4 (strings with no more than 3 characters),
  //  -  s -> s.contains("b") (strings containing "b"),
  // s -> (s.length() % 2) == 0 (strings of even length).

  public static void question3() {
    List<String> words = getList();
    System.out.println("3:");
    // YOUR CODE
    List<String> wordsLen4 = words.stream().filter(s -> s.length() < 4).collect(Collectors.toList());
    System.out.println(wordsLen4);

    List<String> wordsWithb = words.stream().filter(s -> s.contains("b")).collect(Collectors.toList());
    System.out.println(wordsWithb);

    List<String> wordsEvenLen = words.stream().filter(s -> s.length() % 2 == 0).collect(Collectors.toList());
    System.out.println(wordsEvenLen);
    }

  // For each of the following lambda expressions (see Question 7 in Worksheet 2),
  // produce the list that contains the results of applying the function
  // defined by the lambda expression to each element of the original list
  // (use the map stream operation):
  // - s -> s + "!",
  //  s -> s.replace("i", "eye"),
  //  s -> s.toUpperCase().

  public static void question4() {
    List<String> words = getList();
    System.out.println("4:");
    List<String> wordsExclamation = words.stream().map((s) -> s + "!").collect(Collectors.toList());
    System.out.println(wordsExclamation);

    List<String> wordsReplaced = words.stream().map((s) -> s.replace("i", "eye")).collect(Collectors.toList());
    System.out.println(wordsReplaced);

//    List<String> wordsToUpper = words.stream().map((s) -> s.toUpperCase()).collect(Collectors.toList());
//    System.out.println(wordsToUpper);
    words.stream()
            .map((s) -> s.toUpperCase())
            .forEach(System.out::println);
  }


  // (*) Turn the strings in the list into uppercase, keep only the
  // ones that are shorter than four characters, and, of what is remaining,
  // keep only the ones that contain "e", and print the first result.
  // Repeat the process, except checking for a "q" instead of an "e".

  public static void question5() {
    List<String> words = getList();
    System.out.println("5a:");
//    List<String> l1 = words.stream().map((s) -> s.toUpperCase()).filter(s -> s.length() < 4).filter(s -> s.contains("e")).collect(Collectors.toList());
//    System.out.println(l1.get(0));
//
//    List<String> l2 = words.stream().map((s) -> s.toUpperCase()).filter(s -> s.length() < 4).filter(s -> s.contains("q")).collect(Collectors.toList());
//    System.out.println(l2.get(0));
    words.stream()
            .map((s) -> s.toUpperCase())
            .filter(s -> s.length() < 4)
            .filter(s -> s.contains("e"))
            .limit(1)
            .forEach(System.out::println);

    // With optional
    Optional<String> firstStr = words.stream()
            .map((s) -> s.toUpperCase())
            .filter(s -> s.length() < 4)
            .filter(s -> s.contains("e"))
            .findFirst();
    firstStr.ifPresent(System.out::println);

    // check q
    words.stream()
            .map((s) -> s.toUpperCase())
            .filter(s -> s.length() < 4)
            .filter(s -> s.contains("q"))
            .limit(1)
            .forEach(System.out::println);
  }


  // (** ) The above example uses lazy evaluation, but it is not easy to see
  // that it is doing so. Create a variation of the above example that shows
  // that it is doing lazy evaluation. The simplest way is to track which
  // entries are turned into upper case.

  public static void question6() {
    List<String> words = getList();
    System.out.println("6:");
    Optional<String> res = words.stream()
            .map((s) -> s.toUpperCase())
            .peek(s -> System.out.println("Pre-fileter1: " + s))
            .filter(s -> s.length() < 4)
            .peek(s -> System.out.println("Pre-filter2: " + s))
            .filter(s -> s.contains("e"))
            .findFirst();
    res.ifPresent(System.out::println);
  }

  // (*) Produce a single String that is the result of concatenating the
  // uppercase versions of all the Strings.
  // For example, the result should be "HIHELLO...".
  // Hint: use a map operation that turns the words into upper case,
  // followed by a reduce operation that concatenates them.

  public static void question7() {
    List<String> words = getList();
    System.out.println("7:");
    String concat = words.stream()
            .map(String::toUpperCase)
            .reduce("", String::concat); //(a,b)->a+b
    System.out.println(concat);
  }


  // (*) Produce a single String that is the result of concatenating the
  // uppercase versions of all the Strings.
  // For example, the result should be "HIHELLO...".
  // Use a single reduce operation, without using map.

  public static void question8() {
    List<String> words = getList();
    System.out.println("8:");
    String res = words.stream()
            .reduce("", (a,b) -> a + b.toUpperCase());
    // note that only the second parameter needs to be transformed into upper case
    // the first parameter (the accumulated result) is already upper-case
    // e.g, ["hi", "hello", "world"], with "" firstly + "hi".toUpperCase() we get "HI", then + "hello".toUpperCase we get
    // "HIHELLO" etc. Eventually get all the strings concatenated with uppercase.
  }

  // (*) Produce a String that is all the words concatenated together, but
  // with commas in between. For example, the result should be "hi,hello,...".
  // Note that there is no comma at the beginning, before "hi", and also no comma
  // at the end, after the last word.

  public static void question9() {
    List<String> words = getList();
    System.out.println("9:");
    String res = words.stream()
            .collect(Collectors.joining(","));
    System.out.println(res);
  }

  // CONTINUE WITH THE REST OF THE QUESTIONS

  // Use streams to filter the first two meat dishes.
  public static void question10() {
    List<Dish> dishes = Dish.getMenu();
    List<Dish> firstTwo = dishes.stream()
            .filter(d -> d.type() == Dish.Type.MEAT)
            .limit(2)
            .collect(Collectors.toList());
    System.out.println(firstTwo);

  }

  // Count the number of dishes in a stream using the map and reduce methods.
  public static void question11() {
    List<Dish> dishes = Dish.getMenu();
    int nums = dishes.stream()
            .map((d) -> 1)
            .reduce(0, Integer::sum);
    System.out.println(nums);
  }

  // Given a list of numbers, print out the list of the squares
  // of each number. For example, given `[1, 2, 3, 4, 5]` you should print `[1, 4, 9, 16, 25]`.
  public static Integer[] getIntegerArray() {
    return new Integer[] { 1, 7, 3, 4, 8, 2 };
  }
  public static int[] getIntArray() {
    return new int[] { 1, 7, 3, 4, 8, 2 };
  }
  public static void question12() {
    List<Integer> squares = IntStream.of(getIntArray())
                    .mapToObj(i -> i * i).collect(Collectors.toList());
    System.out.println(squares);
  }


  // Given two lists of numbers, print out all pairs of numbers. For example,
  // given a list [1, 2, 3] and a list [3, 4] you should print:
  // [[1, 3], [1, 4], [2, 3], [2, 4], [3, 3], [3, 4]].
  // For simplicity, you can represent each pair as a list with two elements.
  public static void question13() {
    List<Integer> l1 = List.of(1, 2, 3);
    List<Integer> l2 = List.of(3, 4);
    List<List<Integer>> sets = l1.stream()
            .flatMap(  num1 -> l2.stream().map( num2 -> List.of(num1, num2) )   ).collect(Collectors.toList());
    System.out.println(sets);
  }

// Extend the previous example to return only pairs whose
// sum is divisible by `3`. For example, `[2, 4]` and `[3, 3]` are valid.
  public static void question14() {
    List<Integer> l1 = List.of(1, 2, 3);
    List<Integer> l2 = List.of(3, 4);
    List<List<Integer>> sets = l1.stream()
            .flatMap(  num1 -> l2.stream().filter(num2 -> (num1 + num2) % 3 == 0)
                    .map(num2 -> List.of(num1, num2))  ).collect(Collectors.toList());
    System.out.println(sets);
  }

  public static void main(String... args) { // varargs alternative to String[]
    question1();

  }
}