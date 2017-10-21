    public static void main(String[] args) throws Exception {

        TestResult            result;
        TestCase              test;
        java.util.Enumeration failures;
        int                   count;

        result = new TestResult();
        test   = new TestLikePredicateOptimizations("test");

        test.run(result);

        count = result.failureCount();

        System.out.println("TestLikePredicateOptimizations failure count: "
                           + count);

        failures = result.failures();

        while (failures.hasMoreElements()) {
            System.out.println(failures.nextElement());
        }
    }
