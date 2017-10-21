	@Test
	public void handleErrorFrameWithConversionException() throws Exception {
		StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
		accessor.setContentType(MimeTypeUtils.APPLICATION_JSON);
		accessor.addNativeHeader("foo", "bar");
		accessor.setLeaveMutable(true);
		byte[] payload = "{'foo':'bar'}".getBytes(StandardCharsets.UTF_8);

		StompHeaders stompHeaders = StompHeaders.readOnlyStompHeaders(accessor.getNativeHeaders());
		when(this.sessionHandler.getPayloadType(stompHeaders)).thenReturn(Map.class);

		this.session.handleMessage(MessageBuilder.createMessage(payload, accessor.getMessageHeaders()));

		verify(this.sessionHandler).getPayloadType(stompHeaders);
		verify(this.sessionHandler).handleException(same(this.session), same(StompCommand.ERROR),
				eq(stompHeaders), same(payload), any(MessageConversionException.class));
		verifyNoMoreInteractions(this.sessionHandler);
	}
