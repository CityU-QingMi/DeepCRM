	@SuppressWarnings("")
	@Test
	public void startDeferredResultProcessingBeforeConcurrentHandlingException() throws Exception {
		DeferredResult<Integer> deferredResult = new DeferredResult<>();
		Exception exception = new Exception();

		DeferredResultProcessingInterceptor interceptor = mock(DeferredResultProcessingInterceptor.class);
		willThrow(exception).given(interceptor).beforeConcurrentHandling(this.asyncWebRequest, deferredResult);

		this.asyncManager.registerDeferredResultInterceptor("interceptor", interceptor);

		try {
			this.asyncManager.startDeferredResultProcessing(deferredResult);
			fail("Expected Exception");
		}
		catch (Exception success) {
			assertEquals(exception, success);
		}

		assertFalse(this.asyncManager.hasConcurrentResult());

		verify(this.asyncWebRequest).addTimeoutHandler((Runnable) notNull());
		verify(this.asyncWebRequest).addErrorHandler((Consumer<Throwable>) notNull());
		verify(this.asyncWebRequest).addCompletionHandler((Runnable) notNull());
	}
