    @BeforeClass
    public static void setupClass() {
        map.put("test1", "one");
        map.put("test2", "two");
        logEvent0 = Log4jLogEvent.newBuilder() //
                .setLoggerName("test") //
                .setContextMap(map) //
                .setLoggerFqcn("MapRewritePolicyTest.setupClass()") //
                .setLevel(Level.ERROR) //
                .setMessage(new SimpleMessage("Test")) //
                .setThrown(new RuntimeException("test")) //
                .setThreadName("none")
                .setSource(new StackTraceElement("MapRewritePolicyTest", "setupClass", "MapRewritePolicyTest", 28))
                .setTimeMillis(2).build();

        logEvent1 = ((Log4jLogEvent) logEvent0).asBuilder() //
                .setMessage(new StringMapMessage(map)) //
                .setSource(new StackTraceElement("MapRewritePolicyTest", "setupClass", "MapRewritePolicyTest", 29)) //
                .build();

        final ThreadContextStack stack = new MutableThreadContextStack(new ArrayList<>(map.values()));
        logEvent2 = ((Log4jLogEvent) logEvent0).asBuilder() //
                .setContextStack(stack) //
                .setMarker(MarkerManager.getMarker("test")) //
                .setLevel(Level.TRACE) //
                .setMessage(new StructuredDataMessage("test", "Nothing", "test", map)) //
                .setTimeMillis(20000000) //
                .setSource(new StackTraceElement("MapRewritePolicyTest", "setupClass", "MapRewritePolicyTest", 30)) //
                .build();
        logEvent3 = ((Log4jLogEvent) logEvent0).asBuilder() //
                .setContextStack(stack) //
                .setLevel(Level.ALL) //
                .setMessage(new StringMapMessage(map)) //
                .setTimeMillis(Long.MAX_VALUE) //
                .setSource(new StackTraceElement("MapRewritePolicyTest", "setupClass", "MapRewritePolicyTest", 31)) //
                .build();
        rewrite = new KeyValuePair[]{new KeyValuePair("test2", "2"), new KeyValuePair("test3", "three")};
    }
