	@Test
	public void startCallableProcessingErrorAndComplete() throws Exception {
		StubCallable callable = new StubCallable();

		CallableProcessingInterceptor interceptor = mock(CallableProcessingInterceptor.class);
		Exception e = new Exception();
		given(interceptor.handleError(this.asyncWebRequest, callable, e)).willReturn(RESULT_NONE);

		this.asyncManager.registerCallableInterceptor("interceptor", interceptor);
		this.asyncManager.startCallableProcessing(callable);

		AsyncEvent event = new AsyncEvent(new MockAsyncContext(this.servletRequest, this.servletResponse), e);
		this.asyncWebRequest.onError(event);
		this.asyncWebRequest.onComplete(event);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(e, this.asyncManager.getConcurrentResult());

		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, callable);
		verify(interceptor).afterCompletion(this.asyncWebRequest, callable);
	}
