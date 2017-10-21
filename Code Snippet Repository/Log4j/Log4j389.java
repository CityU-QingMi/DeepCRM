    @Test
    public void testInheritanceSwitchedOn() throws Exception {
        System.setProperty(DefaultThreadContextMap.INHERITABLE_MAP, "true");
        try {
            ThreadContext.clearMap();
            ThreadContext.put("Greeting", "Hello");
            StringBuilder sb = new StringBuilder();
            TestThread thread = new TestThread(sb);
            thread.start();
            thread.join();
            String str = sb.toString();
            assertTrue("Unexpected ThreadContext value. Expected Hello. Actual "
                    + str, "Hello".equals(str));
            sb = new StringBuilder();
            thread = new TestThread(sb);
            thread.start();
            thread.join();
            str = sb.toString();
            assertTrue("Unexpected ThreadContext value. Expected Hello. Actual "
                    + str, "Hello".equals(str));
        } finally {
            System.clearProperty(DefaultThreadContextMap.INHERITABLE_MAP);
        }
    }
