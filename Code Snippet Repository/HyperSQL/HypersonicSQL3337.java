    public static void main(String[] argv) {

        TestResult result = new TestResult();
        TestCase   testA  = new TestMerge("testMerge1");
        TestCase   testB  = new TestMerge("testMerge2");
        TestCase   testC  = new TestMerge("testMerge3");
        TestCase   testD  = new TestMerge("testMerge4");
        TestCase   testE  = new TestMerge("testMerge5");
        TestCase   testF  = new TestMerge("testMerge6");
        TestCase   testG  = new TestMerge("testMerge7");
        TestCase   testH  = new TestMerge("testMerge8");
        TestCase   testI  = new TestMerge("testMerge9");

        testA.run(result);
        testB.run(result);
        testC.run(result);
        testD.run(result);
        testE.run(result);
        testF.run(result);
        testG.run(result);
        testH.run(result);
        testI.run(result);
        System.out.println("TestMerge error count: " + result.failureCount());

        Enumeration e = result.failures();

        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }
