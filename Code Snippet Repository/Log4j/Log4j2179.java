    @Test
	public void testCauseSuppressedExceptions() {
		final Exception cause = new Exception("Nested exception");
		cause.addSuppressed(new IOException("Suppressed #1"));
		cause.addSuppressed(new IOException("Suppressed #2"));
		LogManager.getLogger().error("Error", new Exception(cause));
		final ThrowableProxy proxy = new ThrowableProxy(new Exception("Root exception", cause));
		final String extendedStackTraceAsString = proxy.getExtendedStackTraceAsString("same suffix");
		assertTrue(extendedStackTraceAsString.contains("\tSuppressed: java.io.IOException: Suppressed #1"));
		assertTrue(extendedStackTraceAsString.contains("\tSuppressed: java.io.IOException: Suppressed #1"));
	}
