	@Test
	public void startDeferredResultProcessingTimeoutAndComplete() throws Exception {

		DeferredResult<Integer> deferredResult = new DeferredResult<>();

		DeferredResultProcessingInterceptor interceptor = mock(DeferredResultProcessingInterceptor.class);
		given(interceptor.handleTimeout(this.asyncWebRequest, deferredResult)).willReturn(true);

		this.asyncManager.registerDeferredResultInterceptor("interceptor", interceptor);
		this.asyncManager.startDeferredResultProcessing(deferredResult);

		this.asyncWebRequest.onTimeout(ASYNC_EVENT);
		this.asyncWebRequest.onComplete(ASYNC_EVENT);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(AsyncRequestTimeoutException.class, this.asyncManager.getConcurrentResult().getClass());

		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, deferredResult);
		verify(interceptor).preProcess(this.asyncWebRequest, deferredResult);
		verify(interceptor).afterCompletion(this.asyncWebRequest, deferredResult);
	}
