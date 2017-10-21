	@Test
	public void sendDuplicateBeforeHandlerInitialized() throws Exception {
		this.emitter.send("foo", MediaType.TEXT_PLAIN);
		this.emitter.send("foo", MediaType.TEXT_PLAIN);
		this.emitter.complete();
		verifyNoMoreInteractions(this.handler);

		this.emitter.initialize(this.handler);
		verify(this.handler, times(2)).send("foo", MediaType.TEXT_PLAIN);
		verify(this.handler).complete();
		verifyNoMoreInteractions(this.handler);
	}
