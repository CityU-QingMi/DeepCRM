	@Test
	public void closeWithWebSocketHandlerExceptions() throws Exception {
		willThrow(new Exception()).given(this.webSocketHandler).afterConnectionClosed(this.session, CloseStatus.NORMAL);

		this.session.delegateConnectionEstablished();
		this.session.setActive(true);
		this.session.close(CloseStatus.NORMAL);

		assertEquals(CloseStatus.NORMAL, this.session.getCloseStatus());
		assertClosed();
	}
