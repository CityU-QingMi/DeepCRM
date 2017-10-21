    @Test
	public void testDeserializedLogEventWithThrowableProxyButNoThrowable() {
		final ExtendedThrowablePatternConverter converter = ExtendedThrowablePatternConverter.newInstance(null, null);
		final Throwable originalThrowable = new Exception("something bad happened");
		final ThrowableProxy throwableProxy = new ThrowableProxy(originalThrowable);
		final Throwable deserializedThrowable = null;
        final Log4jLogEvent event = Log4jLogEvent.newBuilder() //
                .setLoggerName("testLogger") //
                .setLoggerFqcn(this.getClass().getName()) //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage("")) //
                .setThrown(deserializedThrowable) //
                .setThrownProxy(throwableProxy) //
                .setTimeMillis(0).build();
		final StringBuilder sb = new StringBuilder();
		converter.format(event, sb);
		final String result = sb.toString();
		assertTrue(result, result.contains(originalThrowable.getMessage()));
		assertTrue(result, result.contains(originalThrowable.getStackTrace()[0].getMethodName()));
	}
