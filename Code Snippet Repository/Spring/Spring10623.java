	@SuppressWarnings("")
	@Test
	public void onErrorHandlerAfterOnErrorEvent() throws Exception {
		Consumer<Throwable> handler = mock(Consumer.class);
		this.asyncRequest.addErrorHandler(handler);

		this.asyncRequest.startAsync();
		Exception e = new Exception();
		this.asyncRequest.onError(new AsyncEvent(this.request.getAsyncContext(), e));

		verify(handler).accept(e);
	}
