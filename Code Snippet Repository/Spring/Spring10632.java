	@Test
	public void startDeferredResultProcessingErrorAndResumeThroughCallback() throws Exception {

		final DeferredResult<Throwable> deferredResult = new DeferredResult<>();
		deferredResult.onError(new Consumer<Throwable>() {
			@Override
			public void accept(Throwable t) {
				deferredResult.setResult(t);
			}
		});

		this.asyncManager.startDeferredResultProcessing(deferredResult);

		Exception e = new Exception();
		AsyncEvent event = new AsyncEvent(new MockAsyncContext(this.servletRequest, this.servletResponse), e);
		this.asyncWebRequest.onError(event);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(e, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());
	}
