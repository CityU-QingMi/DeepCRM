	@Test
	public void purgeExpiredRegistries() throws Exception {
		// Prepare broadcast message from remote server
		TestSimpUser testUser = new TestSimpUser("joe");
		testUser.addSessions(new TestSimpSession("remote-sub"));
		SimpUserRegistry testRegistry = mock(SimpUserRegistry.class);
		when(testRegistry.getUsers()).thenReturn(Collections.singleton(testUser));
		Object registryDto = new MultiServerUserRegistry(testRegistry).getLocalRegistryDto();
		Message<?> message = this.converter.toMessage(registryDto, null);

		// Add remote registry
		this.registry.addRemoteRegistryDto(message, this.converter, -1);


		assertEquals(1, this.registry.getUserCount());
		this.registry.purgeExpiredRegistries();
		assertEquals(0, this.registry.getUserCount());
	}
