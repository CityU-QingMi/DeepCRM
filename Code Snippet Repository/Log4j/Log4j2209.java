    static Log4jLogEvent createLogEvent() {
        final Marker cMarker = MarkerManager.getMarker("Marker1");
        final Marker pMarker1 = MarkerManager.getMarker("ParentMarker1");
        final Marker pMarker2 = MarkerManager.getMarker("ParentMarker2");
        final Marker gfMarker = MarkerManager.getMarker("GrandFatherMarker");
        final Marker gmMarker = MarkerManager.getMarker("GrandMotherMarker");
        cMarker.addParents(pMarker1);
        cMarker.addParents(pMarker2);
        pMarker1.addParents(gmMarker);
        pMarker1.addParents(gfMarker);
        final Exception sourceHelper = new Exception();
        sourceHelper.fillInStackTrace();
        final Exception cause = new NullPointerException("testNPEx");
        sourceHelper.fillInStackTrace();
        final StackTraceElement source = sourceHelper.getStackTrace()[0];
        final IOException ioException = new IOException("testIOEx", cause);
        ioException.addSuppressed(new IndexOutOfBoundsException("I am suppressed exception 1"));
        ioException.addSuppressed(new IndexOutOfBoundsException("I am suppressed exception 2"));
        final ThrowableProxy throwableProxy = new ThrowableProxy(ioException);
        final Map<String, String> contextMap = new HashMap<>();
        contextMap.put("MDC.A", "A_Value");
        contextMap.put("MDC.B", "B_Value");
        final DefaultThreadContextStack contextStack = new DefaultThreadContextStack(true);
        contextStack.clear();
        contextStack.push("stack_msg1");
        contextStack.add("stack_msg2");
        final Log4jLogEvent expected = Log4jLogEvent.newBuilder() //
                .setLoggerName("a.B") //
                .setMarker(cMarker) //
                .setLoggerFqcn("f.q.c.n") //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("Msg")) //
                .setThrown(ioException) //
                .setThrownProxy(throwableProxy) //
                .setContextMap(contextMap) //
                .setContextStack(contextStack) //
                .setThreadName("MyThreadName") //
                .setSource(source) //
                .setTimeMillis(1).build();
        // validate event?
        return expected;
    }
