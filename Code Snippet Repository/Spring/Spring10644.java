	@Test
	public void startCallableProcessing() throws Exception {

		int concurrentResult = 21;
		Callable<Object> task = new StubCallable(concurrentResult);

		CallableProcessingInterceptor interceptor = mock(CallableProcessingInterceptor.class);

		setupDefaultAsyncScenario();

		this.asyncManager.registerCallableInterceptor("interceptor", interceptor);
		this.asyncManager.startCallableProcessing(task);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(concurrentResult, this.asyncManager.getConcurrentResult());

		verifyDefaultAsyncScenario();
		verify(interceptor).beforeConcurrentHandling(this.asyncWebRequest, task);
		verify(interceptor).preProcess(this.asyncWebRequest, task);
		verify(interceptor).postProcess(this.asyncWebRequest, task, new Integer(concurrentResult));
	}
