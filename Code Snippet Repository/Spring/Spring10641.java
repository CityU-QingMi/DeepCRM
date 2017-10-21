	@Test
	public void startDeferredResultProcessingNullInput() throws Exception {
		try {
			this.asyncManager.startDeferredResultProcessing((DeferredResult<?>) null);
			fail("Expected exception");
		}
		catch (IllegalArgumentException ex) {
			assertEquals(ex.getMessage(), "DeferredResult must not be null");
		}
	}
