	@Test
	public void startCallableProcessingAfterTimeoutException() throws Exception {

		StubCallable callable = new StubCallable();
		Exception exception = new Exception();

		CallableProcessingInterceptor interceptor = mock(CallableProcessingInterceptor.class);
		given(interceptor.handleTimeout(this.asyncWebRequest, callable)).willThrow(exception);

		this.asyncManager.registerCallableInterceptor("timeoutInterceptor", interceptor);
		this.asyncManager.startCallableProcessing(callable);

		this.asyncWebRequest.onTimeout(ASYNC_EVENT);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(exception, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());

		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, callable);
	}
