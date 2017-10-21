	@Test
	public void retrieveExpiredSession() throws Exception {
		WebSession session = this.store.createWebSession().block();
		assertNotNull(session);
		session.getAttributes().put("foo", "bar");
		session.save().block();

		String id = session.getId();
		WebSession retrieved = this.store.retrieveSession(id).block();
		assertNotNull(retrieved);
		assertSame(session, retrieved);

		// Fast-forward 31 minutes
		this.store.setClock(Clock.offset(this.store.getClock(), Duration.ofMinutes(31)));
		WebSession retrievedAgain = this.store.retrieveSession(id).block();
		assertNull(retrievedAgain);
	}
