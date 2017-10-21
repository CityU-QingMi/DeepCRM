	@Test
	public void expirationChecks() throws Exception {
		// Create 3 sessions
		WebSession session1 = this.store.createWebSession().block();
		assertNotNull(session1);
		session1.start();
		session1.save().block();

		WebSession session2 = this.store.createWebSession().block();
		assertNotNull(session2);
		session2.start();
		session2.save().block();

		WebSession session3 = this.store.createWebSession().block();
		assertNotNull(session3);
		session3.start();
		session3.save().block();

		// Fast-forward 31 minutes
		this.store.setClock(Clock.offset(this.store.getClock(), Duration.ofMinutes(31)));

		// Create 2 more sessions
		WebSession session4 = this.store.createWebSession().block();
		assertNotNull(session4);
		session4.start();
		session4.save().block();

		WebSession session5 = this.store.createWebSession().block();
		assertNotNull(session5);
		session5.start();
		session5.save().block();

		// Retrieve, forcing cleanup of all expired..
		assertNull(this.store.retrieveSession(session1.getId()).block());
		assertNull(this.store.retrieveSession(session2.getId()).block());
		assertNull(this.store.retrieveSession(session3.getId()).block());

		assertNotNull(this.store.retrieveSession(session4.getId()).block());
		assertNotNull(this.store.retrieveSession(session5.getId()).block());
	}
