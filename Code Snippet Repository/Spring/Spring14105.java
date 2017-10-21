	@Test
	public void addMultipleSessionIds() {
		DefaultSimpUserRegistry registry = new DefaultSimpUserRegistry();

		TestPrincipal user = new TestPrincipal("joe");
		Message<byte[]> message = createMessage(SimpMessageType.CONNECT_ACK, "123");
		SessionConnectedEvent event = new SessionConnectedEvent(this, message, user);
		registry.onApplicationEvent(event);

		message = createMessage(SimpMessageType.CONNECT_ACK, "456");
		event = new SessionConnectedEvent(this, message, user);
		registry.onApplicationEvent(event);

		message = createMessage(SimpMessageType.CONNECT_ACK, "789");
		event = new SessionConnectedEvent(this, message, user);
		registry.onApplicationEvent(event);

		SimpUser simpUser = registry.getUser("joe");
		assertNotNull(simpUser);

		assertEquals(1, registry.getUserCount());
		assertEquals(3, simpUser.getSessions().size());
		assertNotNull(simpUser.getSession("123"));
		assertNotNull(simpUser.getSession("456"));
		assertNotNull(simpUser.getSession("789"));
	}
