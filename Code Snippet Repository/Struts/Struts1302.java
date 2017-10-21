    public void testInvalidLogLevel() throws Exception {
        TimerInterceptor real = new TimerInterceptor();
        real.setLogLevel("xxxx");
        real.init();
        try {
            real.intercept(mai);
            fail("Should not have reached this point.");
        } catch (IllegalArgumentException e) {
        	// success
        }
    }
