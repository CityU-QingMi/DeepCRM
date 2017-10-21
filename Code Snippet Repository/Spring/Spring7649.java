	@Test
	public void toNativeHeadersMessageFrame() {
		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.MESSAGE);
		headers.setSubscriptionId("s1");
		headers.setDestination("/d");
		headers.setContentType(MimeTypeUtils.APPLICATION_JSON);
		headers.updateStompCommandAsServerMessage();

		Map<String, List<String>> actual = headers.toNativeHeaderMap();

		assertEquals(actual.toString(), 4, actual.size());
		assertEquals("s1", actual.get(StompHeaderAccessor.STOMP_SUBSCRIPTION_HEADER).get(0));
		assertEquals("/d", actual.get(StompHeaderAccessor.STOMP_DESTINATION_HEADER).get(0));
		assertEquals("application/json", actual.get(StompHeaderAccessor.STOMP_CONTENT_TYPE_HEADER).get(0));
		assertNotNull("message-id was not created", actual.get(StompHeaderAccessor.STOMP_MESSAGE_ID_HEADER).get(0));
	}
