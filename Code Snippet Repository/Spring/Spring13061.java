	@Test
	public void sendWithError() throws Exception {
		this.emitter.initialize(this.handler);
		verify(this.handler).onTimeout(any());
		verify(this.handler).onError(any());
		verify(this.handler).onCompletion(any());
		verifyNoMoreInteractions(this.handler);

		IOException failure = new IOException();
		doThrow(failure).when(this.handler).send("foo", MediaType.TEXT_PLAIN);
		try {
			this.emitter.send("foo", MediaType.TEXT_PLAIN);
			fail("Expected exception");
		}
		catch (IOException ex) {
			// expected
		}
		verify(this.handler).send("foo", MediaType.TEXT_PLAIN);
		verifyNoMoreInteractions(this.handler);
	}
