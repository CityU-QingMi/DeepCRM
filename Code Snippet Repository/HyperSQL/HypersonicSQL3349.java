    public static void main(String[] argv) {

        TestResult result = new TestResult();
        TestCase   testA  = new TestMultiInsert("testMultiInsert");

        testA.run(result);
        System.out.println("TestMultiInsert error count: " + result.failureCount());
        Enumeration e = result.failures();
        while(e.hasMoreElements()) System.out.println(e.nextElement());
    }
