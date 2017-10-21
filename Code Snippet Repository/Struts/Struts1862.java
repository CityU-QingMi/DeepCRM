    public void testOneWaitWithDelay() throws Exception {
        waitInterceptor.setDelay(200);
        waitInterceptor.setDelaySleepInterval(100);

        ActionProxy proxy = buildProxy("action1");
        long before = System.currentTimeMillis();
        String result = proxy.execute();
        long after = System.currentTimeMillis();
        assertEquals("wait", result);
        assertTrue("delay should be ca. 200 millis", (after - before) >= 190);

        Thread.sleep(400);

        ActionProxy proxy2 = buildProxy("action1");
        String result2 = proxy2.execute();
        assertEquals("success", result2);
    }
