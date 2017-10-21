	@Test
	public void lastAccessTimeIsUpdatedOnRetrieve() throws Exception {
		WebSession session1 = this.store.createWebSession().block();
		assertNotNull(session1);
		String id = session1.getId();
		Instant time1 = session1.getLastAccessTime();
		session1.save().block();

		// Fast-forward a few seconds
		this.store.setClock(Clock.offset(this.store.getClock(), Duration.ofSeconds(5)));

		WebSession session2 = this.store.retrieveSession(id).block();
		assertNotNull(session2);
		assertSame(session1, session2);
		Instant time2 = session2.getLastAccessTime();
		assertTrue(time1.isBefore(time2));
	}
