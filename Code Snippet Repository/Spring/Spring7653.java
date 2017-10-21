	@Test
	public void handleMessageWithNoUser() {
		String sourceDestination = "/user/" + "123" + "/queue/foo";
		Message<?> message = createMessage(SimpMessageType.MESSAGE, null, "123", sourceDestination);
		UserDestinationResult actual = this.resolver.resolveDestination(message);

		assertEquals(sourceDestination, actual.getSourceDestination());
		assertEquals(1, actual.getTargetDestinations().size());
		assertEquals("/queue/foo-user123", actual.getTargetDestinations().iterator().next());
		assertEquals("/user/queue/foo", actual.getSubscribeDestination());
		assertNull(actual.getUser());
	}
