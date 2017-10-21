    public void testTwoWait() throws Exception {
        waitInterceptor.setDelay(0);
        waitInterceptor.setDelaySleepInterval(0);

        ActionProxy proxy = buildProxy("action1");
        String result = proxy.execute();
        assertEquals("wait", result);

        Thread.sleep(300);

        ActionProxy proxy2 = buildProxy("action1");
        String result2 = proxy2.execute();
        assertEquals("wait", result2);

        Thread.sleep(500);

        ActionProxy proxy3 = buildProxy("action1");
        String result3 = proxy3.execute();
        assertEquals("success", result3);
    }
