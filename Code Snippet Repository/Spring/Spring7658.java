	@Test
	public void handleSubscribeOneUserMultipleSessions() {

		TestSimpUser simpUser = new TestSimpUser("joe");
		simpUser.addSessions(new TestSimpSession("123"), new TestSimpSession("456"));
		when(this.registry.getUser("joe")).thenReturn(simpUser);

		TestPrincipal user = new TestPrincipal("joe");
		Message<?> message = createMessage(SimpMessageType.SUBSCRIBE, user, "456", "/user/queue/foo");
		UserDestinationResult actual = this.resolver.resolveDestination(message);

		assertEquals(1, actual.getTargetDestinations().size());
		assertEquals("/queue/foo-user456", actual.getTargetDestinations().iterator().next());
	}
