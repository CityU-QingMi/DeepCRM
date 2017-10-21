	@Test
	public void handleSubscribeNoUser() {
		String sourceDestination = "/user/queue/foo";
		Message<?> message = createMessage(SimpMessageType.SUBSCRIBE, null, "123", sourceDestination);
		UserDestinationResult actual = this.resolver.resolveDestination(message);

		assertEquals(sourceDestination, actual.getSourceDestination());
		assertEquals(1, actual.getTargetDestinations().size());
		assertEquals("/queue/foo-user" + "123", actual.getTargetDestinations().iterator().next());
		assertEquals(sourceDestination, actual.getSubscribeDestination());
		assertNull(actual.getUser());
	}
