	@Test
	public void writeFrameIoException() throws Exception {
		this.session.setExceptionOnWrite(new IOException());
		this.session.delegateConnectionEstablished();

		try {
			this.session.writeFrame(SockJsFrame.openFrame());
			fail("expected exception");
		}
		catch (SockJsTransportFailureException ex) {
			assertEquals(CloseStatus.SERVER_ERROR, this.session.getCloseStatus());
			verify(this.webSocketHandler).afterConnectionClosed(this.session, CloseStatus.SERVER_ERROR);
		}
	}
