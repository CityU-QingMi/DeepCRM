    @Test
    public void testStdErrLogFormat() throws UnsupportedEncodingException
    {
        StdErrLog log = new StdErrLog(LogTest.class.getName(),new Properties());
        StdErrCapture output = new StdErrCapture(log);

        log.info("testing:{},{}","test","format1");
        log.info("testing:{}","test","format2");
        log.info("testing","test","format3");
        log.info("testing:{},{}","test",null);
        log.info("testing {} {}",null,null);
        log.info("testing:{}",null,null);
        log.info("testing",null,null);

        System.err.println(output);
        output.assertContains("INFO:oejul.LogTest:tname: testing:test,format1");
        output.assertContains("INFO:oejul.LogTest:tname: testing:test,format1");
        output.assertContains("INFO:oejul.LogTest:tname: testing:test format2");
        output.assertContains("INFO:oejul.LogTest:tname: testing test format3");
        output.assertContains("INFO:oejul.LogTest:tname: testing:test,null");
        output.assertContains("INFO:oejul.LogTest:tname: testing null null");
        output.assertContains("INFO:oejul.LogTest:tname: testing:null");
        output.assertContains("INFO:oejul.LogTest:tname: testing");
    }
