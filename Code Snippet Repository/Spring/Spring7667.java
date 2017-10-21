	@Test
	public void getSessionsWhenUserIsConnectedToMultipleServers() throws Exception {
		// Add user to local registry
		TestSimpUser localUser = new TestSimpUser("joe");
		TestSimpSession localSession = new TestSimpSession("sess123");
		localUser.addSessions(localSession);
		when(this.localRegistry.getUser("joe")).thenReturn(localUser);

		// Prepare broadcast message from remote server
		TestSimpUser remoteUser = new TestSimpUser("joe");
		TestSimpSession remoteSession = new TestSimpSession("sess456");
		remoteUser.addSessions(remoteSession);
		SimpUserRegistry remoteRegistry = mock(SimpUserRegistry.class);
		when(remoteRegistry.getUsers()).thenReturn(Collections.singleton(remoteUser));
		Object remoteRegistryDto = new MultiServerUserRegistry(remoteRegistry).getLocalRegistryDto();
		Message<?> message = this.converter.toMessage(remoteRegistryDto, null);

		// Add remote registry
		this.registry.addRemoteRegistryDto(message, this.converter, 20000);


		assertEquals(1, this.registry.getUserCount());
		SimpUser user = this.registry.getUsers().iterator().next();
		assertTrue(user.hasSessions());
		assertEquals(2, user.getSessions().size());
		assertThat(user.getSessions(), containsInAnyOrder(localSession, remoteSession));
		assertSame(localSession, user.getSession("sess123"));
		assertEquals(remoteSession, user.getSession("sess456"));

		user = this.registry.getUser("joe");
		assertEquals(2, user.getSessions().size());
		assertThat(user.getSessions(), containsInAnyOrder(localSession, remoteSession));
		assertSame(localSession, user.getSession("sess123"));
		assertEquals(remoteSession, user.getSession("sess456"));
	}
