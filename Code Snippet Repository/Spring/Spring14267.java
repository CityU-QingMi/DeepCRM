	@Test
	public void closeWhenNotOpen() throws Exception {
		assertNew();

		this.session.close();
		assertNull("Close not ignored for a new session", this.session.getCloseStatus());

		this.session.delegateConnectionEstablished();
		assertOpen();

		this.session.close();
		assertClosed();
		assertEquals(3000, this.session.getCloseStatus().getCode());

		this.session.close(CloseStatus.SERVER_ERROR);
		assertEquals("Close should be ignored if already closed", 3000, this.session.getCloseStatus().getCode());
	}
