	@Test
	public void createWithUnubscribeNativeHeaders() {
		MultiValueMap<String, String> extHeaders = new LinkedMultiValueMap<>();
		extHeaders.add(StompHeaderAccessor.STOMP_ID_HEADER, "s1");

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.UNSUBSCRIBE, extHeaders);

		assertEquals(StompCommand.UNSUBSCRIBE, headers.getCommand());
		assertEquals(SimpMessageType.UNSUBSCRIBE, headers.getMessageType());
		assertEquals("s1", headers.getSubscriptionId());
	}
