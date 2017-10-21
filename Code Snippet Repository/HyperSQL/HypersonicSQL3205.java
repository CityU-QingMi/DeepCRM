    public static void main(String[] argv) {

        StopWatch    sw   = new StopWatch();
        TestAllTypes test = new TestAllTypes();

        test.setUp();
        test.testFillUp();

//        test.tearDown();
        test.checkResults();
        System.out.println("Total Test Time: " + sw.elapsedTime());
    }
