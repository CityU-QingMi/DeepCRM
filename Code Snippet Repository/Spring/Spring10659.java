	@Test
	public void startDeferredResultProcessingTimeoutAndResumeWithDefaultResult() throws Exception {

		DeferredResult<Integer> deferredResult = new DeferredResult<>(null, 23);
		this.asyncManager.startDeferredResultProcessing(deferredResult);

		AsyncEvent event = null;
		this.asyncWebRequest.onTimeout(event);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(23, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());
	}
