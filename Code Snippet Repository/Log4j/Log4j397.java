    public static void perfTest() throws Exception {
        ThreadContext.clearMap();
        final Timer complete = new Timer("ThreadContextTest");
        complete.start();
        ThreadContext.put("Var1", "value 1");
        ThreadContext.put("Var2", "value 2");
        ThreadContext.put("Var3", "value 3");
        ThreadContext.put("Var4", "value 4");
        ThreadContext.put("Var5", "value 5");
        ThreadContext.put("Var6", "value 6");
        ThreadContext.put("Var7", "value 7");
        ThreadContext.put("Var8", "value 8");
        ThreadContext.put("Var9", "value 9");
        ThreadContext.put("Var10", "value 10");
        final int loopCount = 1000000;
        final Timer timer = new Timer("ThreadContextCopy", loopCount);
        timer.start();
        for (int i = 0; i < loopCount; ++i) {
            final Map<String, String> map = ThreadContext.getImmutableContext();
            assertNotNull(map);
        }
        timer.stop();
        complete.stop();
        System.out.println(timer.toString());
        System.out.println(complete.toString());
    }
