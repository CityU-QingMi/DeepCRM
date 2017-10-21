	@Test
	public void sendAfterHandlerInitialized() throws Exception {
		this.emitter.initialize(this.handler);
		verify(this.handler).onTimeout(any());
		verify(this.handler).onError(any());
		verify(this.handler).onCompletion(any());
		verifyNoMoreInteractions(this.handler);

		this.emitter.send("foo", MediaType.TEXT_PLAIN);
		this.emitter.send("bar", MediaType.TEXT_PLAIN);
		this.emitter.complete();

		verify(this.handler).send("foo", MediaType.TEXT_PLAIN);
		verify(this.handler).send("bar", MediaType.TEXT_PLAIN);
		verify(this.handler).complete();
		verifyNoMoreInteractions(this.handler);
	}
