	@Test
	public void getUserFromRemoteRegistry() throws Exception {
		// Prepare broadcast message from remote server
		TestSimpUser testUser = new TestSimpUser("joe");
		TestSimpSession testSession = new TestSimpSession("remote-sess");
		testSession.addSubscriptions(new TestSimpSubscription("remote-sub", "/remote-dest"));
		testUser.addSessions(testSession);
		SimpUserRegistry testRegistry = mock(SimpUserRegistry.class);
		when(testRegistry.getUsers()).thenReturn(Collections.singleton(testUser));
		Object registryDto = new MultiServerUserRegistry(testRegistry).getLocalRegistryDto();
		Message<?> message = this.converter.toMessage(registryDto, null);

		// Add remote registry
		this.registry.addRemoteRegistryDto(message, this.converter, 20000);

		assertEquals(1, this.registry.getUserCount());
		SimpUser user = this.registry.getUser("joe");
		assertNotNull(user);
		assertTrue(user.hasSessions());
		assertEquals(1, user.getSessions().size());
		SimpSession session = user.getSession("remote-sess");
		assertNotNull(session);
		assertEquals("remote-sess", session.getId());
		assertSame(user, session.getUser());
		assertEquals(1, session.getSubscriptions().size());
		SimpSubscription subscription = session.getSubscriptions().iterator().next();
		assertEquals("remote-sub", subscription.getId());
		assertSame(session, subscription.getSession());
		assertEquals("/remote-dest", subscription.getDestination());
	}
