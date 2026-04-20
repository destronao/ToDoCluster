package tests;

public class TestRunner {

    public static void main(String[] args) {
        System.out.println("=== Ejecutando suite de tests ===\n");

        System.out.println("--- TaskTests ---");
        TaskTests.main(args);
        System.out.println();

        System.out.println("--- InMemoryTaskRepositoryTest ---");
        InMemoryTaskRepositoryTest.main(args);
        System.out.println();

        System.out.println("=== Suite de tests completada ===");
    }
}
