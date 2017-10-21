    public void testTwoWaitWithDelay() throws Exception {
        waitInterceptor.setDelay(100);
        waitInterceptor.setDelaySleepInterval(100);

        ActionProxy proxy = buildProxy("action1");
        long before = System.currentTimeMillis();
        String result = proxy.execute();
        long after = System.currentTimeMillis();
        assertEquals("wait", result);
        assertTrue("delay should be ca. 100 millis", (after - before) >= 90);

        Thread.sleep(100);

        ActionProxy proxy2 = buildProxy("action1");
        long before2 = System.currentTimeMillis();
        String result2 = proxy2.execute();
        long after2 = System.currentTimeMillis();
        assertEquals("wait", result2);
        assertTrue("there should be no delay", (after2 - before2) < 110);

        Thread.sleep(400);

        ActionProxy proxy3 = buildProxy("action1");
        String result3 = proxy3.execute();
        assertEquals("success", result3);
    }
