    @Test
    public void testPatternSelector() throws Exception {
        final PatternMatch[] patterns = new PatternMatch[1];
        patterns[0] = new PatternMatch("FLOW", "%d %-5p [%t]: ====== %C{1}.%M:%L %m ======%n");
        final PatternSelector selector = MarkerPatternSelector.createSelector(patterns, "%d %-5p [%t]: %m%n", true, true, ctx.getConfiguration());
        final PatternLayout layout = PatternLayout.newBuilder().withPatternSelector(selector)
                .withConfiguration(ctx.getConfiguration()).build();
        final LogEvent event1 = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.layout.PatternSelectorTest$FauxLogger")
                .setMarker(MarkerManager.getMarker("FLOW"))
                .setLevel(Level.TRACE) //
                .setIncludeLocation(true)
                .setMessage(new SimpleMessage("entry")).build();
        final String result1 = new FauxLogger().formatEvent(event1, layout);
        final String expectSuffix1 = String.format("====== PatternSelectorTest.testPatternSelector:53 entry ======%n");
        assertTrue("Unexpected result: " + result1, result1.endsWith(expectSuffix1));
        final LogEvent event2 = Log4jLogEvent.newBuilder() //
                .setLoggerName(this.getClass().getName()).setLoggerFqcn("org.apache.logging.log4j.core.Logger") //
                .setLevel(Level.INFO) //
                .setMessage(new SimpleMessage("Hello, world 1!")).build();
        final String result2 = new String(layout.toByteArray(event2));
        final String expectSuffix2 = String.format("Hello, world 1!%n");
        assertTrue("Unexpected result: " + result2, result2.endsWith(expectSuffix2));
    }
