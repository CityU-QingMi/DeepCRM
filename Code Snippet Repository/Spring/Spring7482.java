	@Test
	public void sendToWithDestinationPlaceholders() throws Exception {
		given(this.messageChannel.send(any(Message.class))).willReturn(true);

		Map<String, String> vars = new LinkedHashMap<>(1);
		vars.put("roomName", "roomA");

		String sessionId = "sess1";
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create();
		accessor.setSessionId(sessionId);
		accessor.setSubscriptionId("sub1");
		accessor.setHeader(DestinationVariableMethodArgumentResolver.DESTINATION_TEMPLATE_VARIABLES_HEADER, vars);
		Message<?> message = MessageBuilder.createMessage(PAYLOAD, accessor.getMessageHeaders());
		this.handler.handleReturnValue(PAYLOAD, this.sendToWithPlaceholdersReturnType, message);

		verify(this.messageChannel, times(1)).send(this.messageCaptor.capture());

		SimpMessageHeaderAccessor actual = getCapturedAccessor(0);
		assertEquals(sessionId, actual.getSessionId());
		assertEquals("/topic/chat.message.filtered.roomA", actual.getDestination());
	}
