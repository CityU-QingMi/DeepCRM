	@Test
	public void startCallableProcessingAfterException() throws Exception {

		StubCallable callable = new StubCallable();
		Exception exception = new Exception();

		CallableProcessingInterceptor interceptor = mock(CallableProcessingInterceptor.class);
		Exception e = new Exception();
		given(interceptor.handleError(this.asyncWebRequest, callable, e)).willThrow(exception);

		this.asyncManager.registerCallableInterceptor("errorInterceptor", interceptor);
		this.asyncManager.startCallableProcessing(callable);

		AsyncEvent event = new AsyncEvent(new MockAsyncContext(this.servletRequest, this.servletResponse), e);
		this.asyncWebRequest.onError(event);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(exception, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());

		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, callable);
	}
