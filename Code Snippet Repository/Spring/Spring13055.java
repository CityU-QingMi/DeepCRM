	@Test
	public void sendBeforeHandlerInitialized() throws Exception {
		this.emitter.send("foo", MediaType.TEXT_PLAIN);
		this.emitter.send("bar", MediaType.TEXT_PLAIN);
		this.emitter.complete();
		verifyNoMoreInteractions(this.handler);

		this.emitter.initialize(this.handler);
		verify(this.handler).send("foo", MediaType.TEXT_PLAIN);
		verify(this.handler).send("bar", MediaType.TEXT_PLAIN);
		verify(this.handler).complete();
		verifyNoMoreInteractions(this.handler);
	}
