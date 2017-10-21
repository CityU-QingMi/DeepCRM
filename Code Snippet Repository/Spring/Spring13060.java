	@Test
	public void sendAfterHandlerInitializedWithError() throws Exception {
		this.emitter.initialize(this.handler);
		verify(this.handler).onTimeout(any());
		verify(this.handler).onError(any());
		verify(this.handler).onCompletion(any());
		verifyNoMoreInteractions(this.handler);

		IllegalStateException ex = new IllegalStateException();
		this.emitter.send("foo", MediaType.TEXT_PLAIN);
		this.emitter.send("bar", MediaType.TEXT_PLAIN);
		this.emitter.completeWithError(ex);

		verify(this.handler).send("foo", MediaType.TEXT_PLAIN);
		verify(this.handler).send("bar", MediaType.TEXT_PLAIN);
		verify(this.handler).completeWithError(ex);
		verifyNoMoreInteractions(this.handler);
	}
