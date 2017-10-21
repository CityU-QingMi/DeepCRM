    public void testWaitDelayAndJobAlreadyDone2() throws Exception {
        waitInterceptor.setDelay(1500);
        waitInterceptor.setDelaySleepInterval(200); // just takes a little longer to find out job is done

        ActionProxy proxy = buildProxy("action1");
        long before = System.currentTimeMillis();
        String result = proxy.execute();
        long diff = System.currentTimeMillis() - before;
        assertEquals("success", result);
        assertTrue("Job done already after 500 so there should not be such long delay", diff <= 1000);
    }
