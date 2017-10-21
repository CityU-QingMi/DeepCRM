    @Test
    public void testStdErrLogDebug()
    {
        StdErrLog log = new StdErrLog("xxx",new Properties());
        StdErrCapture output = new StdErrCapture(log);

        log.setLevel(StdErrLog.LEVEL_DEBUG);
        log.debug("testing {} {}","test","debug");
        log.info("testing {} {}","test","info");
        log.warn("testing {} {}","test","warn");
        log.setLevel(StdErrLog.LEVEL_INFO);
        log.debug("YOU SHOULD NOT SEE THIS!",null,null);

        // Test for backward compat with old (now deprecated) method
        Logger before = log.getLogger("before");
        log.setDebugEnabled(true);
        Logger after = log.getLogger("after");
        before.debug("testing {} {}","test","debug-before");
        log.debug("testing {} {}","test","debug-deprecated");
        after.debug("testing {} {}","test","debug-after");

        log.setDebugEnabled(false);
        before.debug("testing {} {}","test","debug-before-false");
        log.debug("testing {} {}","test","debug-deprecated-false");
        after.debug("testing {} {}","test","debug-after-false");

        output.assertContains("DBUG:xxx:tname: testing test debug");
        output.assertContains("INFO:xxx:tname: testing test info");
        output.assertContains("WARN:xxx:tname: testing test warn");
        output.assertNotContains("YOU SHOULD NOT SEE THIS!");
        output.assertContains("DBUG:x.before:tname: testing test debug-before");
        output.assertContains("DBUG:xxx:tname: testing test debug-deprecated");
        output.assertContains("DBUG:x.after:tname: testing test debug-after");
        output.assertNotContains("DBUG:x.before:tname: testing test debug-before-false");
        output.assertNotContains("DBUG:xxx:tname: testing test debug-deprecated-false");
        output.assertNotContains("DBUG:x.after:tname: testing test debug-after-false");
    }
