	@Test
	public void sendToUserDefaultDestinationWhenUsingDotPathSeparator() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		TestUser user = new TestUser();
		Message<?> inputMessage = createMessage("sess1", "sub1", "/app/", "dest.foo.bar", user);
		this.handler.handleReturnValue(PAYLOAD, this.sendToUserDefaultDestReturnType, inputMessage);

		verify(this.messageChannel, times(1)).send(this.messageCaptor.capture());

		SimpMessageHeaderAccessor accessor = getCapturedAccessor(0);
		assertEquals("/user/" + user.getName() + "/queue/dest.foo.bar", accessor.getDestination());
	}
