	@Test
	public void nullSessionId() throws Exception {
		DefaultSimpUserRegistry registry = new DefaultSimpUserRegistry();

		TestPrincipal user = new TestPrincipal("joe");
		Message<byte[]> message = createMessage(SimpMessageType.CONNECT_ACK, "123");
		SessionConnectedEvent event = new SessionConnectedEvent(this, message, user);
		registry.onApplicationEvent(event);

		SimpUser simpUser = registry.getUser("joe");
		assertNull(simpUser.getSession(null));
	}
