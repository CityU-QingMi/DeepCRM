    static protected TestCacheSize populate() {
        TestCacheSize  test  = new TestCacheSize();

/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/

        test.filepath = "mem:test";
        test.filedb   = false;
        test.shutdown = false;

        test.setUp();
        test.testFillUp();
        //test.checkResults();
        //System.out.println("total test time -- " + sw.elapsedTime() + " ms");
        return test;
    }
