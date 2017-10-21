	@Test
	public void sendToDefaultDestinationWhenUsingDotPathSeparator() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		Message<?> inputMessage = createMessage("sess1", "sub1", "/app/", "dest.foo.bar", null);
		this.handler.handleReturnValue(PAYLOAD, this.sendToDefaultDestReturnType, inputMessage);

		verify(this.messageChannel, times(1)).send(this.messageCaptor.capture());

		SimpMessageHeaderAccessor accessor = getCapturedAccessor(0);
		assertEquals("/topic/dest.foo.bar", accessor.getDestination());
	}
