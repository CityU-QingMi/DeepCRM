	@Test
	public void createWithSubscribeNativeHeaders() {
		MultiValueMap<String, String> extHeaders = new LinkedMultiValueMap<>();
		extHeaders.add(StompHeaderAccessor.STOMP_ID_HEADER, "s1");
		extHeaders.add(StompHeaderAccessor.STOMP_DESTINATION_HEADER, "/d");

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SUBSCRIBE, extHeaders);

		assertEquals(StompCommand.SUBSCRIBE, headers.getCommand());
		assertEquals(SimpMessageType.SUBSCRIBE, headers.getMessageType());
		assertEquals("/d", headers.getDestination());
		assertEquals("s1", headers.getSubscriptionId());
	}
