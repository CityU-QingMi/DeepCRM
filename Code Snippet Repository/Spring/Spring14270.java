	@Test
	public void closeWithWriteFrameExceptions() throws Exception {
		this.session.setExceptionOnWrite(new IOException());

		this.session.delegateConnectionEstablished();
		this.session.setActive(true);
		this.session.close();

		assertEquals(new CloseStatus(3000, "Go away!"), this.session.getCloseStatus());
		assertClosed();
	}
