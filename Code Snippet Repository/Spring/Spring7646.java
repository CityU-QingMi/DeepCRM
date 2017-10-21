	@Test
	public void createWithConnectNativeHeaders() {
		MultiValueMap<String, String> extHeaders = new LinkedMultiValueMap<>();
		extHeaders.add(StompHeaderAccessor.STOMP_LOGIN_HEADER, "joe");
		extHeaders.add(StompHeaderAccessor.STOMP_PASSCODE_HEADER, "joe123");

		StompHeaderAccessor headerAccessor = StompHeaderAccessor.create(StompCommand.CONNECT, extHeaders);

		assertEquals(StompCommand.CONNECT, headerAccessor.getCommand());
		assertEquals(SimpMessageType.CONNECT, headerAccessor.getMessageType());
		assertNotNull(headerAccessor.getHeader("stompCredentials"));
		assertEquals("joe", headerAccessor.getLogin());
		assertEquals("joe123", headerAccessor.getPasscode());
		assertThat(headerAccessor.toString(), CoreMatchers.containsString("passcode=[PROTECTED]"));

		Map<String, List<String>> output = headerAccessor.toNativeHeaderMap();
		assertEquals("joe", output.get(StompHeaderAccessor.STOMP_LOGIN_HEADER).get(0));
		assertEquals("PROTECTED", output.get(StompHeaderAccessor.STOMP_PASSCODE_HEADER).get(0));
	}
