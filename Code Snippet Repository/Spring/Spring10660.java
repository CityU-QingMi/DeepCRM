	@Test
	public void startDeferredResultProcessingTimeoutAndResumeThroughCallback() throws Exception {

		final DeferredResult<Integer> deferredResult = new DeferredResult<>();
		deferredResult.onTimeout(new Runnable() {
			@Override
			public void run() {
				deferredResult.setResult(23);
			}
		});

		this.asyncManager.startDeferredResultProcessing(deferredResult);

		AsyncEvent event = null;
		this.asyncWebRequest.onTimeout(event);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(23, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());
	}
