	@Test
	public void broadcastRegistry() throws Exception {

		TestSimpUser simpUser1 = new TestSimpUser("joe");
		TestSimpUser simpUser2 = new TestSimpUser("jane");

		simpUser1.addSessions(new TestSimpSession("123"));
		simpUser1.addSessions(new TestSimpSession("456"));

		HashSet<SimpUser> simpUsers = new HashSet<>(Arrays.asList(simpUser1, simpUser2));
		when(this.localRegistry.getUsers()).thenReturn(simpUsers);

		getUserRegistryTask().run();

		ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
		verify(this.brokerChannel).send(captor.capture());

		Message<?> message = captor.getValue();
		assertNotNull(message);
		MessageHeaders headers = message.getHeaders();
		assertEquals("/topic/simp-user-registry", SimpMessageHeaderAccessor.getDestination(headers));

		MultiServerUserRegistry remoteRegistry = new MultiServerUserRegistry(mock(SimpUserRegistry.class));
		remoteRegistry.addRemoteRegistryDto(message, this.converter, 20000);
		assertEquals(2, remoteRegistry.getUserCount());
		assertNotNull(remoteRegistry.getUser("joe"));
		assertNotNull(remoteRegistry.getUser("jane"));
	}
