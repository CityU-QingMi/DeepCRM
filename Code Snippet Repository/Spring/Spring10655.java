	@Test
	public void startCallableProcessingTimeoutAndResumeThroughInterceptor() throws Exception {

		StubCallable callable = new StubCallable();

		CallableProcessingInterceptor interceptor = mock(CallableProcessingInterceptor.class);
		given(interceptor.handleTimeout(this.asyncWebRequest, callable)).willReturn(22);

		this.asyncManager.registerCallableInterceptor("timeoutInterceptor", interceptor);
		this.asyncManager.startCallableProcessing(callable);

		this.asyncWebRequest.onTimeout(ASYNC_EVENT);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(22, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());

		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, callable);
	}
