	@Test
	public void onOpen() throws Throwable {
		given(this.session.getId()).willReturn("123");
		this.adapter.onOpen(this.session, null);

		verify(this.webSocketHandler).afterConnectionEstablished(this.webSocketSession);
		verify(this.session, atLeast(2)).addMessageHandler(any(MessageHandler.Whole.class));

		given(this.session.getId()).willReturn("123");
		assertEquals("123", this.webSocketSession.getId());
	}
