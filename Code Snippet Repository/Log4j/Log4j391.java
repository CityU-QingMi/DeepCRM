    @Test
    public void testInheritanceSwitchedOffByDefault() throws Exception {
        ThreadContext.clearMap();
        ThreadContext.put("Greeting", "Hello");
        StringBuilder sb = new StringBuilder();
        TestThread thread = new TestThread(sb);
        thread.start();
        thread.join();
        String str = sb.toString();
        assertTrue("Unexpected ThreadContext value. Expected null. Actual "
                + str, "null".equals(str));
        sb = new StringBuilder();
        thread = new TestThread(sb);
        thread.start();
        thread.join();
        str = sb.toString();
        assertTrue("Unexpected ThreadContext value. Expected null. Actual "
                + str, "null".equals(str));
    }
