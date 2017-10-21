	@Test
	public void close() throws Exception {
		this.session.delegateConnectionEstablished();
		assertOpen();

		this.session.setActive(true);
		this.session.close();

		assertEquals(1, this.session.getSockJsFramesWritten().size());
		assertEquals(SockJsFrame.closeFrameGoAway(), this.session.getSockJsFramesWritten().get(0));

		assertEquals(1, this.session.getNumberOfLastActiveTimeUpdates());
		assertTrue(this.session.didCancelHeartbeat());

		assertEquals(new CloseStatus(3000, "Go away!"), this.session.getCloseStatus());
		assertClosed();
		verify(this.webSocketHandler).afterConnectionClosed(this.session, new CloseStatus(3000, "Go away!"));
	}
