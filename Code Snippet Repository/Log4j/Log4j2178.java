    @Test
	public void testSuppressedExceptions() {
		final Exception e = new Exception("Root exception");
		e.addSuppressed(new IOException("Suppressed #1"));
		e.addSuppressed(new IOException("Suppressed #2"));
		LogManager.getLogger().error("Error", e);
		final ThrowableProxy proxy = new ThrowableProxy(e);
		final String extendedStackTraceAsString = proxy.getExtendedStackTraceAsString("same suffix");
		assertTrue(extendedStackTraceAsString.contains("\tSuppressed: java.io.IOException: Suppressed #1"));
		assertTrue(extendedStackTraceAsString.contains("\tSuppressed: java.io.IOException: Suppressed #1"));
	}
