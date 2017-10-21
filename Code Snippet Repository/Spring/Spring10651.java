	@Test
	public void startDeferredResultProcessingTimeoutAndResumeThroughInterceptor() throws Exception {

		DeferredResult<Integer> deferredResult = new DeferredResult<>();

		DeferredResultProcessingInterceptor interceptor = new DeferredResultProcessingInterceptor() {
			@Override
			public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> result) throws Exception {
				result.setErrorResult(23);
				return true;
			}
		};

		this.asyncManager.registerDeferredResultInterceptor("interceptor", interceptor);
		this.asyncManager.startDeferredResultProcessing(deferredResult);

		AsyncEvent event = null;
		this.asyncWebRequest.onTimeout(event);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(23, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());
	}
