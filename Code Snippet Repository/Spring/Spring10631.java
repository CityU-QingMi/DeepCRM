	@Test
	public void startDeferredResultProcessingErrorAndResumeWithDefaultResult() throws Exception {

		Exception e = new Exception();
		DeferredResult<Throwable> deferredResult = new DeferredResult<>(null, e);
		this.asyncManager.startDeferredResultProcessing(deferredResult);

		AsyncEvent event = new AsyncEvent(new MockAsyncContext(this.servletRequest, this.servletResponse), e);
		this.asyncWebRequest.onError(event);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(e, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());
	}
