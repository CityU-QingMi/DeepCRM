	@Test
	public void startDeferredResultProcessingErrorAndComplete() throws Exception {

		DeferredResult<Integer> deferredResult = new DeferredResult<>();

		DeferredResultProcessingInterceptor interceptor = mock(DeferredResultProcessingInterceptor.class);
		Exception e = new Exception();
		given(interceptor.handleError(this.asyncWebRequest, deferredResult, e)).willReturn(true);

		this.asyncManager.registerDeferredResultInterceptor("interceptor", interceptor);
		this.asyncManager.startDeferredResultProcessing(deferredResult);

		AsyncEvent event = new AsyncEvent(new MockAsyncContext(this.servletRequest, this.servletResponse), e);
		this.asyncWebRequest.onError(event);
		this.asyncWebRequest.onComplete(event);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(e, this.asyncManager.getConcurrentResult());

		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, deferredResult);
		verify(interceptor).preProcess(this.asyncWebRequest, deferredResult);
		verify(interceptor).afterCompletion(this.asyncWebRequest, deferredResult);
	}
