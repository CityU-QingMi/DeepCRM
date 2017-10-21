	@Test
	public void handleMessageToOtherUser() {

		TestSimpUser otherSimpUser = new TestSimpUser("anna");
		otherSimpUser.addSessions(new TestSimpSession("456"));
		when(this.registry.getUser("anna")).thenReturn(otherSimpUser);

		TestPrincipal user = new TestPrincipal("joe");
		TestPrincipal otherUser = new TestPrincipal("anna");
		String sourceDestination = "/user/anna/queue/foo";
		Message<?> message = createMessage(SimpMessageType.MESSAGE, user, "456", sourceDestination);

		UserDestinationResult actual = this.resolver.resolveDestination(message);

		assertEquals(sourceDestination, actual.getSourceDestination());
		assertEquals(1, actual.getTargetDestinations().size());
		assertEquals("/queue/foo-user456", actual.getTargetDestinations().iterator().next());
		assertEquals("/user/queue/foo", actual.getSubscribeDestination());
		assertEquals(otherUser.getName(), actual.getUser());
	}
