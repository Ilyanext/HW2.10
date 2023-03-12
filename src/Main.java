import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {
    public static class Numbers {
        private int numbers;

        public Numbers(int numbers) {
            this.numbers = numbers;
        }

        public int getNumbers() {
            return numbers;
        }

        public void setNumbers(int numbers) {
            this.numbers = numbers;
        }

        @Override
        public String toString() {
            return "Число: " + numbers;
        }
    }

    public static class MaterialNumbers {
        private double number;

        public MaterialNumbers(double number) {
            this.number = number;
        }

        public double getNumber() {
            return number;
        }

        public void setNumber(double number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "Число: " + number;
        }
    }

    public static class Person {
        private String fullName;
        private int age;

        public Person(String fullName, int age) {
            this.fullName = fullName;
            this.age = age;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Имя: " + fullName + '\'' +
                    " возвраст: " + age;
        }
    }

    public static void print() {
        System.out.println("===========");
    }

    public static void println() {
        System.out.println();
    }

    public static void task1_anonim() {
        Numbers numbers1 = new Numbers(1);
        Numbers numbers2 = new Numbers(2);
        Numbers numbers3 = new Numbers(-2);
        Numbers numbers4 = new Numbers(-3);

        List<Numbers> list = new ArrayList<>();
        list.add(numbers1);
        list.add(numbers2);
        list.add(numbers3);
        list.add(numbers4);

        testPredicate number = new testPredicate() {

            @Override
            public boolean test(int numbers) {
                if (numbers >= 0) {
                    return true;
                }
                return false;
            }
        };

        for (Numbers numbers : list) {
            System.out.println(number.test(numbers.getNumbers()));
        }
    }

    public static void task1_lambda() {
        Numbers numbers1 = new Numbers(1);
        Numbers numbers2 = new Numbers(2);
        Numbers numbers3 = new Numbers(-2);
        Numbers numbers4 = new Numbers(-3);

        List<Numbers> list = new ArrayList<>();
        list.add(numbers1);
        list.add(numbers2);
        list.add(numbers3);
        list.add(numbers4);

        testPredicate number = numbers -> {
            return numbers >= 0 ? true : false;
        };
        for (Numbers numbers : list) {
            System.out.println(number.test(numbers.getNumbers()));
        }
    }

    public static void task2_anonim() {
        Person person1 = new Person("Igla", 23);
        Person person2 = new Person("Igor", 22);
        Person person3 = new Person("Ivan", 2);

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);

        Consumer<Person> consumer = new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println("Hi! " + person.getFullName());
            }
        };

        for (Person person : list) {
            consumer.accept(person);
        }
    }

    public static void task2_lambda() {
        Person person1 = new Person("Igla", 23);
        Person person2 = new Person("Igor", 22);
        Person person3 = new Person("Ivan", 2);

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        Consumer<Person> consumer = (person) -> System.out.println("Hi! " + person.getFullName());

        for (Person person : list) {
            consumer.accept(person);
        }
    }

    public static void task3_anonim() {
        MaterialNumbers namber1 = new MaterialNumbers(1.1);
        MaterialNumbers namber2 = new MaterialNumbers(4.4);
        MaterialNumbers namber3 = new MaterialNumbers(5.5);
        MaterialNumbers namber4 = new MaterialNumbers(7.7);
        MaterialNumbers namber5 = new MaterialNumbers(7.9);

        List<MaterialNumbers> list = new ArrayList<>();
        list.add(namber1);
        list.add(namber2);
        list.add(namber3);
        list.add(namber4);
        list.add(namber5);

        Function<MaterialNumbers, Long> function = new Function<MaterialNumbers, Long>() {
            @Override
            public Long apply(MaterialNumbers materialNumbers) {
                return (long) materialNumbers.getNumber();
            }
        };
        for (MaterialNumbers number : list) {
            System.out.println(function.apply(number));
        }
    }

    public static void task3_lambda() {
        MaterialNumbers namber1 = new MaterialNumbers(1.1);
        MaterialNumbers namber2 = new MaterialNumbers(4.4);
        MaterialNumbers namber3 = new MaterialNumbers(5.5);
        MaterialNumbers namber4 = new MaterialNumbers(7.7);
        MaterialNumbers namber5 = new MaterialNumbers(7.9);

        List<MaterialNumbers> list = new ArrayList<>();
        list.add(namber1);
        list.add(namber2);
        list.add(namber3);
        list.add(namber4);
        list.add(namber5);

        Function<MaterialNumbers, Long> function = (namber) -> {
            return (long) namber.getNumber();
        };
        for (MaterialNumbers number : list) {
            System.out.println(function.apply(number));
        }
    }

    public static void task4_anonim() {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                int a = (int) (Math.random() * ((100 - 0) + 0));
                return a;
            }
        };
        System.out.println(supplier.get());
    }

    public static void task4_lambda() {
        Supplier<Integer> supplier = () -> (int) (Math.random() * ((100 - 0) + 0));
        System.out.println(supplier.get());
    }

    static Predicate<Integer> proverka = t -> t >= 0;
    static Function<Integer, String> function1 = x -> x + " положительное число!";
    static Function<Integer, String> function2 = x -> x + " отрицательное число!";

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        return x -> (condition.test((Integer) x) ? ifTrue.apply(x) : ifFalse.apply(x));
    }

    @FunctionalInterface
    interface Predicate<T> {
        boolean test(int t);
    }

    @FunctionalInterface
    interface testPredicate {
        boolean test(int numbers);
    }

    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    @FunctionalInterface
    public interface Supplier<T> {
        T get();

    }

    public static void main(String[] args) {
        task1_anonim();
        println();
        task1_lambda();
        print();
        task2_anonim();
        println();
        task2_lambda();
        print();
        task3_anonim();
        println();
        task3_lambda();
        print();
        task4_anonim();
        println();
        task4_lambda();
        print();
        println();
        System.out.println(ternaryOperator(proverka, function1, function2).apply(-2));
    }
}
