	@Test
	public void addOneSessionId() {
		TestPrincipal user = new TestPrincipal("joe");
		Message<byte[]> message = createMessage(SimpMessageType.CONNECT_ACK, "123");
		SessionConnectedEvent event = new SessionConnectedEvent(this, message, user);

		DefaultSimpUserRegistry registry = new DefaultSimpUserRegistry();
		registry.onApplicationEvent(event);

		SimpUser simpUser = registry.getUser("joe");
		assertNotNull(simpUser);

		assertEquals(1, registry.getUserCount());
		assertEquals(1, simpUser.getSessions().size());
		assertNotNull(simpUser.getSession("123"));
	}
