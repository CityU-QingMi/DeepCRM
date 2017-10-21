	@Test
	public void startCallableProcessingErrorAndResumeThroughCallback() throws Exception {

		StubCallable callable = new StubCallable();
		WebAsyncTask<Object> webAsyncTask = new WebAsyncTask<>(callable);
		webAsyncTask.onError(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return 7;
			}
		});

		this.asyncManager.startCallableProcessing(webAsyncTask);

		Exception e = new Exception();
		AsyncEvent event = new AsyncEvent(new MockAsyncContext(this.servletRequest, this.servletResponse), e);
		this.asyncWebRequest.onError(event);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(7, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());
	}
