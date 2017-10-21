    public static void main(String[] argv) {

        TestCacheSize  test  = new TestCacheSize();
        HsqlProperties props = HsqlProperties.argArrayToProps(argv, "test");

        test.bigops   = props.getIntegerProperty("test.bigops", test.bigops);
        test.bigrows  = test.bigops;
        test.smallops = test.bigops / 8;
        test.cacheScale = props.getIntegerProperty("test.scale",
                test.cacheScale);
        test.tableType = props.getProperty("test.tabletype", test.tableType);
        test.nioMode   = props.isPropertyTrue("test.nio", test.nioMode);

        if (props.getProperty("test.dbtype", "").equals("mem")) {
            test.filepath = "mem:test";
            test.filedb   = false;
            test.shutdown = false;
        }

        test.setUp();

        StopWatch sw = new StopWatch();

        test.testFillUp();
        test.checkResults();

        long time = sw.elapsedTime();

        test.storeResult("total test time", 0, (int) time, 0);
        System.out.println("total test time -- " + sw.elapsedTime() + " ms");
        test.tearDown();
    }
