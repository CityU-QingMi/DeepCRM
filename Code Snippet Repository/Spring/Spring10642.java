	@Test
	public void startAsyncProcessingWithoutAsyncWebRequest() throws Exception {
		WebAsyncManager manager = WebAsyncUtils.getAsyncManager(new MockHttpServletRequest());

		try {
			manager.startCallableProcessing(new StubCallable(1));
			fail("Expected exception");
		}
		catch (IllegalStateException ex) {
			assertEquals(ex.getMessage(), "AsyncWebRequest must not be null");
		}

		try {
			manager.startDeferredResultProcessing(new DeferredResult<String>());
			fail("Expected exception");
		}
		catch (IllegalStateException ex) {
			assertEquals(ex.getMessage(), "AsyncWebRequest must not be null");
		}
	}
