	@Test
	public void startCallableProcessingErrorAndResumeThroughInterceptor() throws Exception {

		StubCallable callable = new StubCallable();

		CallableProcessingInterceptor interceptor = mock(CallableProcessingInterceptor.class);
		Exception e = new Exception();
		given(interceptor.handleError(this.asyncWebRequest, callable, e)).willReturn(22);

		this.asyncManager.registerCallableInterceptor("errorInterceptor", interceptor);
		this.asyncManager.startCallableProcessing(callable);

		AsyncEvent event = new AsyncEvent(new MockAsyncContext(this.servletRequest, this.servletResponse), e);
		this.asyncWebRequest.onError(event);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(22, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());

		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, callable);
	}
