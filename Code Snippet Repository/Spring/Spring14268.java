	@Test
	public void closeWhenNotActive() throws Exception {
		this.session.delegateConnectionEstablished();
		assertOpen();

		this.session.setActive(false);
		this.session.close();

		assertEquals(Collections.emptyList(), this.session.getSockJsFramesWritten());
	}
