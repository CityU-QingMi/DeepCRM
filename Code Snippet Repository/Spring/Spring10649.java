	@Test
	public void startCallableProcessingPostProcessContinueAfterException() throws Exception {
		Callable<Object> task = new StubCallable(21);
		Exception exception = new Exception();

		CallableProcessingInterceptor interceptor1 = mock(CallableProcessingInterceptor.class);
		CallableProcessingInterceptor interceptor2 = mock(CallableProcessingInterceptor.class);
		willThrow(exception).given(interceptor2).postProcess(this.asyncWebRequest, task, 21);

		setupDefaultAsyncScenario();

		this.asyncManager.registerCallableInterceptors(interceptor1, interceptor2);
		this.asyncManager.startCallableProcessing(task);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(exception, this.asyncManager.getConcurrentResult());

		verifyDefaultAsyncScenario();
		verify(interceptor1).beforeConcurrentHandling(this.asyncWebRequest, task);
		verify(interceptor1).preProcess(this.asyncWebRequest, task);
		verify(interceptor1).postProcess(this.asyncWebRequest, task, 21);
		verify(interceptor2).beforeConcurrentHandling(this.asyncWebRequest, task);
		verify(interceptor2).preProcess(this.asyncWebRequest, task);
	}
