	@Test
	public void toNativeHeadersContentType() {
		SimpMessageHeaderAccessor simpHeaderAccessor = SimpMessageHeaderAccessor.create();
		simpHeaderAccessor.setContentType(MimeType.valueOf("application/atom+xml"));
		Message<byte[]> message = MessageBuilder.createMessage(new byte[0], simpHeaderAccessor.getMessageHeaders());

		StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);
		Map<String, List<String>> map = stompHeaderAccessor.toNativeHeaderMap();

		assertEquals("application/atom+xml", map.get(StompHeaderAccessor.STOMP_CONTENT_TYPE_HEADER).get(0));
	}
