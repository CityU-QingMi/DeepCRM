	@Test
	public void handleErrorFrame() throws Exception {
		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
		accessor.setContentType(new MimeType("text", "plain", StandardCharsets.UTF_8));
		accessor.addNativeHeader("foo", "bar");
		accessor.setLeaveMutable(true);
		String payload = "Oops";

		StompHeaders stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		when(this.sessionHandler.getPayloadType(stompHeaders)).thenReturn(String.class);

		this.session.handleMessage(MessageBuilder.createMessage(payload.getBytes(StandardCharsets.UTF_8), accessor.getMessageHeaders()));

		verify(this.sessionHandler).getPayloadType(stompHeaders);
		verify(this.sessionHandler).handleFrame(stompHeaders, payload);
		verifyNoMoreInteractions(this.sessionHandler);
	}
