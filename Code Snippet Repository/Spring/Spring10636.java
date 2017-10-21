	@Test
	public void startCallableProcessingNullInput() throws Exception {
		try {
			this.asyncManager.startCallableProcessing((Callable<?>) null);
			fail("Expected exception");
		}
		catch (IllegalArgumentException ex) {
			assertEquals(ex.getMessage(), "Callable must not be null");
		}
	}
