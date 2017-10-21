	@Test
	public void getTimeSinceLastActive() throws Exception {
		Thread.sleep(1);

		long time1 = this.session.getTimeSinceLastActive();
		assertTrue(time1 > 0);

		Thread.sleep(1);

		long time2 = this.session.getTimeSinceLastActive();
		assertTrue(time2 > time1);

		this.session.delegateConnectionEstablished();

		Thread.sleep(1);

		this.session.setActive(false);
		assertTrue(this.session.getTimeSinceLastActive() > 0);

		this.session.setActive(true);
		assertEquals(0, this.session.getTimeSinceLastActive());
	}
