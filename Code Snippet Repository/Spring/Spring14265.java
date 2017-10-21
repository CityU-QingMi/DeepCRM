	@Test
	public void delegateMessages() throws Exception {
		String msg1 = "message 1";
		String msg2 = "message 2";
		this.session.delegateMessages(msg1, msg2);

		verify(this.webSocketHandler).handleMessage(this.session, new TextMessage(msg1));
		verify(this.webSocketHandler).handleMessage(this.session, new TextMessage(msg2));
		verifyNoMoreInteractions(this.webSocketHandler);
	}
