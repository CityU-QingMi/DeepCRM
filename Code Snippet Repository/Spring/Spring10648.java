	@Test
	public void startCallableProcessingPostProcessException() throws Exception {
		Callable<Object> task = new StubCallable(21);
		Exception exception = new Exception();

		CallableProcessingInterceptor interceptor = mock(CallableProcessingInterceptor.class);
		willThrow(exception).given(interceptor).postProcess(this.asyncWebRequest, task, 21);

		setupDefaultAsyncScenario();

		this.asyncManager.registerCallableInterceptor("interceptor", interceptor);
		this.asyncManager.startCallableProcessing(task);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(exception, this.asyncManager.getConcurrentResult());

		verifyDefaultAsyncScenario();
		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, task);
		verify(interceptor).preProcess(this.asyncWebRequest, task);
	}
