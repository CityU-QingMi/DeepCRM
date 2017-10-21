    public static void main(String[] argv) {

        TestResult result = new TestResult();
        TestCase   testC  = new TestSqlPersistent("testInsertObject");
        TestCase   testD  = new TestSqlPersistent("testSelectObject");

        testC.run(result);
        testD.run(result);
        System.out.println("TestSqlPersistent error count: "
                           + result.failureCount());
    }
