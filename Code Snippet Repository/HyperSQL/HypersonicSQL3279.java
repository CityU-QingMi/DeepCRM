    public static void main(String[] argv) {

        TestResult result        = new TestResult();
        TestCase   testKeepAlive = new TestHTTPKeepAlive("testKeepAlive");

        testKeepAlive.run(result);
        System.out.println("TestKeepAlive error count: "
                           + result.failureCount());

        Enumeration e = result.failures();

        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }
