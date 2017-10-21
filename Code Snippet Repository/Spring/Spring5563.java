	@Test
	@SuppressWarnings({"", ""})
	public void cancelDoesNotNotifyCallbacksOnSetException() {
		ListenableFutureCallback callback = mock(ListenableFutureCallback.class);
		settableListenableFuture.addCallback(callback);
		settableListenableFuture.cancel(true);

		verify(callback).onFailure(any(CancellationException.class));
		verifyNoMoreInteractions(callback);

		settableListenableFuture.setException(new RuntimeException());
		verifyNoMoreInteractions(callback);

		assertTrue(settableListenableFuture.isCancelled());
		assertTrue(settableListenableFuture.isDone());
	}
