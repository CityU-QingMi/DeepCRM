	@Test
	public void onCompletionHandlerAfterOnCompleteEvent() throws Exception {
		Runnable handler = mock(Runnable.class);
		this.asyncRequest.addCompletionHandler(handler);

		this.asyncRequest.startAsync();
		this.asyncRequest.onComplete(new AsyncEvent(this.request.getAsyncContext()));

		verify(handler).run();
		assertTrue(this.asyncRequest.isAsyncComplete());
	}
