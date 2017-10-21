	@SuppressWarnings("")
	@Test
	public void startCallableProcessingBeforeConcurrentHandlingException() throws Exception {
		Callable<Object> task = new StubCallable(21);
		Exception exception = new Exception();

		CallableProcessingInterceptor interceptor = mock(CallableProcessingInterceptor.class);
		willThrow(exception).given(interceptor).beforeConcurrentHandling(this.asyncWebRequest, task);

		this.asyncManager.registerCallableInterceptor("interceptor", interceptor);

		try {
			this.asyncManager.startCallableProcessing(task);
			fail("Expected Exception");
		}
		catch (Exception ex) {
			assertEquals(exception, ex);
		}

		assertFalse(this.asyncManager.hasConcurrentResult());

		verify(this.asyncWebRequest).addTimeoutHandler((Runnable) notNull());
		verify(this.asyncWebRequest).addErrorHandler((Consumer<Throwable>) notNull());
		verify(this.asyncWebRequest).addCompletionHandler((Runnable) notNull());
	}
