	@Test
	public void startDeferredResultProcessingPostProcessException() throws Exception {
		DeferredResult<Integer> deferredResult = new DeferredResult<>();
		Exception exception = new Exception();

		DeferredResultProcessingInterceptor interceptor = mock(DeferredResultProcessingInterceptor.class);
		willThrow(exception).given(interceptor).postProcess(this.asyncWebRequest, deferredResult, 25);

		setupDefaultAsyncScenario();

		this.asyncManager.registerDeferredResultInterceptor("interceptor", interceptor);
		this.asyncManager.startDeferredResultProcessing(deferredResult);

		deferredResult.setResult(25);

		assertEquals(exception, this.asyncManager.getConcurrentResult());
		verifyDefaultAsyncScenario();
		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, deferredResult);
		verify(interceptor).preProcess(this.asyncWebRequest, deferredResult);
	}
