	private void assertExceptionContains(String msg) throws Exception {
		try {
			listener.beforeTestMethod(testContext);
			fail("Should have thrown an IllegalStateException.");
		}
		catch (IllegalStateException e) {
			// System.err.println(e.getMessage());
			assertTrue("Exception message should contain: " + msg, e.getMessage().contains(msg));
		}
	}
