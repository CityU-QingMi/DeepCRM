	@Test
	public void removeSessionIds() {
		DefaultSimpUserRegistry registry = new DefaultSimpUserRegistry();

		TestPrincipal user = new TestPrincipal("joe");
		Message<byte[]> message = createMessage(SimpMessageType.CONNECT_ACK, "123");
		SessionConnectedEvent connectedEvent = new SessionConnectedEvent(this, message, user);
		registry.onApplicationEvent(connectedEvent);

		message = createMessage(SimpMessageType.CONNECT_ACK, "456");
		connectedEvent = new SessionConnectedEvent(this, message, user);
		registry.onApplicationEvent(connectedEvent);

		message = createMessage(SimpMessageType.CONNECT_ACK, "789");
		connectedEvent = new SessionConnectedEvent(this, message, user);
		registry.onApplicationEvent(connectedEvent);

		SimpUser simpUser = registry.getUser("joe");
		assertNotNull(simpUser);
		assertEquals(3, simpUser.getSessions().size());

		CloseStatus status = CloseStatus.GOING_AWAY;
		message = createMessage(SimpMessageType.DISCONNECT, "456");
		SessionDisconnectEvent disconnectEvent = new SessionDisconnectEvent(this, message, "456", status, user);
		registry.onApplicationEvent(disconnectEvent);

		message = createMessage(SimpMessageType.DISCONNECT, "789");
		disconnectEvent = new SessionDisconnectEvent(this, message, "789", status, user);
		registry.onApplicationEvent(disconnectEvent);

		assertEquals(1, simpUser.getSessions().size());
		assertNotNull(simpUser.getSession("123"));
	}
