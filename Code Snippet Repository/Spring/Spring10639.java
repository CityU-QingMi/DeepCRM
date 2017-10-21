	@Test
	public void startDeferredResultProcessingPreProcessException() throws Exception {

		DeferredResult<Integer> deferredResult = new DeferredResult<>();
		Exception exception = new Exception();

		DeferredResultProcessingInterceptor interceptor = mock(DeferredResultProcessingInterceptor.class);
		willThrow(exception).given(interceptor).preProcess(this.asyncWebRequest, deferredResult);

		setupDefaultAsyncScenario();

		this.asyncManager.registerDeferredResultInterceptor("interceptor", interceptor);
		this.asyncManager.startDeferredResultProcessing(deferredResult);

		deferredResult.setResult(25);

		assertEquals(exception, this.asyncManager.getConcurrentResult());
		verifyDefaultAsyncScenario();
		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, deferredResult);
	}
