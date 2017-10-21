	@Test
	public void handleFrameMessageWithWebSocketHandlerException() throws Exception {
		this.session.handleFrame(SockJsFrame.openFrame().getContent());
		willThrow(new IllegalStateException("Fake error")).given(this.handler).handleMessage(this.session, new TextMessage("foo"));
		willThrow(new IllegalStateException("Fake error")).given(this.handler).handleMessage(this.session, new TextMessage("bar"));
		this.session.handleFrame(SockJsFrame.messageFrame(CODEC, "foo", "bar").getContent());
		assertThat(this.session.isOpen(), equalTo(true));
		verify(this.handler).afterConnectionEstablished(this.session);
		verify(this.handler).handleMessage(this.session, new TextMessage("foo"));
		verify(this.handler).handleMessage(this.session, new TextMessage("bar"));
		verifyNoMoreInteractions(this.handler);
	}
