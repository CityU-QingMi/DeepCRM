    @Test
    public void testSuppressed()
    {
        StdErrLog log = new StdErrLog("xxx",new Properties());
        StdErrCapture output = new StdErrCapture(log);

        Exception inner = new Exception("inner");
        inner.addSuppressed( new IllegalStateException(){{addSuppressed(new Exception("branch0"));}});
        IOException outer = new IOException("outer",inner);
        
        outer.addSuppressed( new IllegalStateException(){{addSuppressed(new Exception("branch1"));}});
        outer.addSuppressed( new IllegalArgumentException(){{addSuppressed(new Exception("branch2"));}});
        
        log.warn("problem",outer);

        output.assertContains("\t|\t|java.lang.Exception: branch2");
        output.assertContains("\t|\t|java.lang.Exception: branch1");
        output.assertContains("\t|\t|java.lang.Exception: branch0");
    }
