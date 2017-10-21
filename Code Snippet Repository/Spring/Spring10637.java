	@Test
	public void startDeferredResultProcessing() throws Exception {
		DeferredResult<String> deferredResult = new DeferredResult<>(1000L);
		String concurrentResult = "abc";

		DeferredResultProcessingInterceptor interceptor = mock(DeferredResultProcessingInterceptor.class);

		setupDefaultAsyncScenario();

		this.asyncManager.registerDeferredResultInterceptor("interceptor", interceptor);
		this.asyncManager.startDeferredResultProcessing(deferredResult);

		deferredResult.setResult(concurrentResult);

		assertEquals(concurrentResult, this.asyncManager.getConcurrentResult());
		verifyDefaultAsyncScenario();
		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, deferredResult);
		verify(interceptor).preProcess(this.asyncWebRequest, deferredResult);
		verify(interceptor).postProcess(asyncWebRequest, deferredResult, concurrentResult);
		verify(this.asyncWebRequest).setTimeout(1000L);
	}
