    protected void assertLogEvent(final LogEvent logEvent) {
        assertThat(logEvent, is(notNullValue()));
        assertThat(logEvent.getTimeMillis(), equalTo(1493121664118L));
        assertThat(logEvent.getThreadName(), equalTo("main"));
        assertThat(logEvent.getThreadId(), equalTo(1L));
        assertThat(logEvent.getThreadPriority(), equalTo(5));
        assertThat(logEvent.getLevel(), equalTo(Level.INFO));
        assertThat(logEvent.getLoggerName(), equalTo("HelloWorld"));
        assertThat(logEvent.getMarker().getName(), equalTo("child"));
        assertThat(logEvent.getMarker().getParents()[0].getName(), equalTo("parent"));
        assertThat(logEvent.getMarker().getParents()[0].getParents()[0].getName(),
                equalTo("grandparent"));
        assertThat(logEvent.getMessage().getFormattedMessage(), equalTo("Hello, world!"));
        assertThat(logEvent.getThrown(), is(nullValue()));
        assertThat(logEvent.getThrownProxy().getMessage(), equalTo("error message"));
        assertThat(logEvent.getThrownProxy().getName(), equalTo("java.lang.RuntimeException"));
        assertThat(logEvent.getThrownProxy().getExtendedStackTrace()[0].getClassName(),
                equalTo("logtest.Main"));
        assertThat(logEvent.getLoggerFqcn(), equalTo("org.apache.logging.log4j.spi.AbstractLogger"));
        assertThat(logEvent.getContextStack().asList(), equalTo(Arrays.asList("one", "two")));
        assertThat((String) logEvent.getContextData().getValue("foo"), equalTo("FOO"));
        assertThat((String) logEvent.getContextData().getValue("bar"), equalTo("BAR"));
        assertThat(logEvent.getSource().getClassName(), equalTo("logtest.Main"));
    }
