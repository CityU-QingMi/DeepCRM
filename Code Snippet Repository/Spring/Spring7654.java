	@Test
	public void ignoreMessage() {

		// no destination
		TestPrincipal user = new TestPrincipal("joe");
		Message<?> message = createMessage(SimpMessageType.MESSAGE, user, "123", null);
		UserDestinationResult actual = this.resolver.resolveDestination(message);
		assertNull(actual);

		// not a user destination
		message = createMessage(SimpMessageType.MESSAGE, user, "123", "/queue/foo");
		actual = this.resolver.resolveDestination(message);
		assertNull(actual);

		// subscribe + not a user destination
		message = createMessage(SimpMessageType.SUBSCRIBE, user, "123", "/queue/foo");
		actual = this.resolver.resolveDestination(message);
		assertNull(actual);

		// no match on message type
		message = createMessage(SimpMessageType.CONNECT, user, "123", "user/joe/queue/foo");
		actual = this.resolver.resolveDestination(message);
		assertNull(actual);
	}
