	@Test
	public void modifyCustomNativeHeader() {
		MultiValueMap<String, String> extHeaders = new LinkedMultiValueMap<>();
		extHeaders.add(StompHeaderAccessor.STOMP_ID_HEADER, "s1");
		extHeaders.add(StompHeaderAccessor.STOMP_DESTINATION_HEADER, "/d");
		extHeaders.add("accountId", "ABC123");

		StompHeaderAccessor headers = StompHeaderAccessor.create(StompCommand.SUBSCRIBE, extHeaders);
		String accountId = headers.getFirstNativeHeader("accountId");
		headers.setNativeHeader("accountId", accountId.toLowerCase());

		Map<String, List<String>> actual = headers.toNativeHeaderMap();
		assertEquals(3, actual.size());

		assertEquals("s1", actual.get(StompHeaderAccessor.STOMP_ID_HEADER).get(0));
		assertEquals("/d", actual.get(StompHeaderAccessor.STOMP_DESTINATION_HEADER).get(0));
		assertNotNull("abc123", actual.get("accountId").get(0));
	}
