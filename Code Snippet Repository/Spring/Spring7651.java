	@Test
	public void encodeConnectWithLoginAndPasscode() throws UnsupportedEncodingException {
		MultiValueMap<String, String> extHeaders = new LinkedMultiValueMap<>();
		extHeaders.add(StompHeaderAccessor.STOMP_LOGIN_HEADER, "joe");
		extHeaders.add(StompHeaderAccessor.STOMP_PASSCODE_HEADER, "joe123");

		StompHeaderAccessor headerAccessor = StompHeaderAccessor.create(StompCommand.CONNECT, extHeaders);
		Message<byte[]> message = MessageBuilder.createMessage(new byte[0], headerAccessor.getMessageHeaders());
		byte[] bytes = new StompEncoder().encode(message);

		assertEquals("CONNECT\nlogin:joe\npasscode:joe123\n\n\0", new String(bytes, "UTF-8"));
	}
