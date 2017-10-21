	@Test
	public void startCallableProcessingTimeoutAndComplete() throws Exception {
		StubCallable callable = new StubCallable();

		CallableProcessingInterceptor interceptor = mock(CallableProcessingInterceptor.class);
		given(interceptor.handleTimeout(this.asyncWebRequest, callable)).willReturn(RESULT_NONE);

		this.asyncManager.registerCallableInterceptor("interceptor", interceptor);
		this.asyncManager.startCallableProcessing(callable);

		this.asyncWebRequest.onTimeout(ASYNC_EVENT);
		this.asyncWebRequest.onComplete(ASYNC_EVENT);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(AsyncRequestTimeoutException.class, this.asyncManager.getConcurrentResult().getClass());

		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, callable);
		verify(interceptor).afterCompletion(this.asyncWebRequest, callable);
	}
