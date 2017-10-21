	@Test
	public void sendBeforeHandlerInitializedWithError() throws Exception {
		IllegalStateException ex = new IllegalStateException();
		this.emitter.send("foo", MediaType.TEXT_PLAIN);
		this.emitter.send("bar", MediaType.TEXT_PLAIN);
		this.emitter.completeWithError(ex);
		verifyNoMoreInteractions(this.handler);

		this.emitter.initialize(this.handler);
		verify(this.handler).send("foo", MediaType.TEXT_PLAIN);
		verify(this.handler).send("bar", MediaType.TEXT_PLAIN);
		verify(this.handler).completeWithError(ex);
		verifyNoMoreInteractions(this.handler);
	}
