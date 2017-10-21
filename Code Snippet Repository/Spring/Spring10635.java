	@SuppressWarnings("")
	@Test
	public void startCallableProcessingWithAsyncTask() throws Exception {
		AsyncTaskExecutor executor = mock(AsyncTaskExecutor.class);
		given(this.asyncWebRequest.getNativeRequest(HttpServletRequest.class)).willReturn(this.servletRequest);

		WebAsyncTask<Object> asyncTask = new WebAsyncTask<>(1000L, executor, mock(Callable.class));
		this.asyncManager.startCallableProcessing(asyncTask);

		verify(executor).submit((Runnable) notNull());
		verify(this.asyncWebRequest).setTimeout(1000L);
		verify(this.asyncWebRequest).addTimeoutHandler(any(Runnable.class));
		verify(this.asyncWebRequest).addErrorHandler(any(Consumer.class));
		verify(this.asyncWebRequest).addCompletionHandler(any(Runnable.class));
		verify(this.asyncWebRequest).startAsync();
	}
