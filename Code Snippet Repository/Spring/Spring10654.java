	@Test
	public void startCallableProcessingTimeoutAndResumeThroughCallback() throws Exception {

		StubCallable callable = new StubCallable();
		WebAsyncTask<Object> webAsyncTask = new WebAsyncTask<>(callable);
		webAsyncTask.onTimeout(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return 7;
			}
		});

		this.asyncManager.startCallableProcessing(webAsyncTask);

		this.asyncWebRequest.onTimeout(ASYNC_EVENT);

		assertTrue(this.asyncManager.hasConcurrentResult());
		assertEquals(7, this.asyncManager.getConcurrentResult());
		assertEquals("/test", ((MockAsyncContext) this.servletRequest.getAsyncContext()).getDispatchedPath());
	}
