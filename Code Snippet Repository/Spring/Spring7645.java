	@Test
	public void createWithMessageFrameNativeHeaders() {
		MultiValueMap<String, String> extHeaders = new LinkedMultiValueMap<>();
		extHeaders.add(StompHeaderAccessor.DESTINATION_HEADER, "/d");
		extHeaders.add(StompHeaderAccessor.STOMP_SUBSCRIPTION_HEADER, "s1");
		extHeaders.add(StompHeaderAccessor.STOMP_CONTENT_TYPE_HEADER, "application/json");

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.MESSAGE, extHeaders);

		assertEquals(StompCommand.MESSAGE, headers.getCommand());
		assertEquals(SimpMessageType.MESSAGE, headers.getMessageType());
		assertEquals("s1", headers.getSubscriptionId());
	}
