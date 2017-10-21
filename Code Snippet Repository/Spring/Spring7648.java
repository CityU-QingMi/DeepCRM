	@Test
	public void toNativeHeadersUnsubscribe() {
		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.UNSUBSCRIBE);
		headers.setSubscriptionId("s1");

		Map<String, List<String>> actual = headers.toNativeHeaderMap();

		assertEquals(1, actual.size());
		assertEquals("s1", actual.get(StompHeaderAccessor.STOMP_ID_HEADER).get(0));
	}
