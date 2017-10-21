	@Test
	public void isActive() throws Exception {
		assertFalse(this.session.isActive());

		this.session.initializeDelegateSession(this.webSocketSession);
		assertTrue(this.session.isActive());

		this.webSocketSession.setOpen(false);
		assertFalse(this.session.isActive());
	}
