	@Test
	public void handleFrameOpen() throws Exception {
		assertThat(this.session.isOpen(), is(false));
		this.session.handleFrame(SockJsFrame.openFrame().getContent());
		assertThat(this.session.isOpen(), is(true));
		assertTrue(this.connectFuture.isDone());
		assertThat(this.connectFuture.get(), sameInstance(this.session));
		verify(this.handler).afterConnectionEstablished(this.session);
		verifyNoMoreInteractions(this.handler);
	}
