    public static void main(String[] argv) {

        TestResult result = new TestResult();
        TestCase   testA  = new TestSql("testMetaData");
        TestCase   testB  = new TestSql("testDoubleNaN");
        TestCase   testC  = new TestSql("testAny");

        testA.run(result);
        testB.run(result);
        testC.run(result);
        System.out.println("TestSql error count: " + result.failureCount());
    }
