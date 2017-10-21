    public static void main(String[] args) {

        TestDataStructures test = new TestDataStructures("testLists");

        for (int i = 0; i < NUMBER_OF_TEST_RUNS; i++) {
            test.testLists();

            if (i % 1000 == 0) {
                System.out.println("Finished " + i + " tests");
                System.out.flush();
            }
        }

        System.out.println(
            "After " + NUMBER_OF_TEST_RUNS + " tests of a maximum of "
            + NUMBER_OF_ITERATIONS_PER_RUN
            + " list commands each test, the list tests passed");
        test.testGrowth();
    }
