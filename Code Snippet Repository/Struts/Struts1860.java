    public void testOneWait() throws Exception {
        waitInterceptor.setDelay(0);
        waitInterceptor.setDelaySleepInterval(0);

        ActionProxy proxy = buildProxy("action1");
        String result = proxy.execute();
        assertEquals("wait", result);

        Thread.sleep(1000);

        ActionProxy proxy2 = buildProxy("action1");
        String result2 = proxy2.execute();
        assertEquals("success", result2);
    }
