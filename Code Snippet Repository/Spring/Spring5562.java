	@Test
	@SuppressWarnings({"", ""})
	public void cancelDoesNotNotifyCallbacksOnSet() {
		ListenableFutureCallback callback = mock(ListenableFutureCallback.class);
		settableListenableFuture.addCallback(callback);
		settableListenableFuture.cancel(true);

		verify(callback).onFailure(any(CancellationException.class));
		verifyNoMoreInteractions(callback);

		settableListenableFuture.set("hello");
		verifyNoMoreInteractions(callback);

		assertTrue(settableListenableFuture.isCancelled());
		assertTrue(settableListenableFuture.isDone());
	}
