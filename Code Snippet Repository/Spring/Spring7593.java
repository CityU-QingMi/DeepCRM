	@Test
	public void handleErrorFrameWithEmptyPayload() throws Exception {
		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
		accessor.addNativeHeader("foo", "bar");
		accessor.setLeaveMutable(true);
		this.session.handleMessage(MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders()));

		StompHeaders stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		verify(this.sessionHandler).handleFrame(stompHeaders, null);
		verifyNoMoreInteractions(this.sessionHandler);
	}
