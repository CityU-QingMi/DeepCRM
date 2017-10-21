	@SuppressWarnings("")
	@Test
	public void startCallableProcessingTimeoutAndCheckThreadInterrupted() throws Exception {

		StubCallable callable = new StubCallable();
		Future future = mock(Future.class);

		AsyncTaskExecutor executor = mock(AsyncTaskExecutor.class);
		when(executor.submit(any(Runnable.class))).thenReturn(future);

		this.asyncManager.setTaskExecutor(executor);
		this.asyncManager.startCallableProcessing(callable);

		this.asyncWebRequest.onTimeout(ASYNC_EVENT);

		assertTrue(this.asyncManager.hasConcurrentResult());

		verify(future).cancel(true);
		verifyNoMoreInteractions(future);
	}
