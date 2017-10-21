	@Test
	public void handleMessage() throws Exception {

		TestSimpUser simpUser1 = new TestSimpUser("joe");
		TestSimpUser simpUser2 = new TestSimpUser("jane");

		simpUser1.addSessions(new TestSimpSession("123"));
		simpUser2.addSessions(new TestSimpSession("456"));

		HashSet<SimpUser> simpUsers = new HashSet<>(Arrays.asList(simpUser1, simpUser2));
		SimpUserRegistry remoteUserRegistry = mock(SimpUserRegistry.class);
		when(remoteUserRegistry.getUserCount()).thenReturn(2);
		when(remoteUserRegistry.getUsers()).thenReturn(simpUsers);

		MultiServerUserRegistry remoteRegistry = new MultiServerUserRegistry(remoteUserRegistry);
		Message<?> message = this.converter.toMessage(remoteRegistry.getLocalRegistryDto(), null);

		this.handler.handleMessage(message);

		assertEquals(2, remoteRegistry.getUserCount());
		assertNotNull(this.multiServerRegistry.getUser("joe"));
		assertNotNull(this.multiServerRegistry.getUser("jane"));
	}
