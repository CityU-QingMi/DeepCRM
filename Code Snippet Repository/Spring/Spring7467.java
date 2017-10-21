	@Test
	public void convertAndSendWithCustomHeaderNonNative() {
		Map<String, Object> headers = new HashMap<>();
		headers.put("key", "value");
		headers.put(NativeMessageHeaderAccessor.NATIVE_HEADERS, new LinkedMultiValueMap<String, String>());
		this.messagingTemplate.convertAndSend("/foo", "data", headers);

		List<Message<byte[]>> messages = this.messageChannel.getMessages();

		SimpMessageHeaderAccessor headerAccessor =
				MessageHeaderAccessor.getAccessor(messages.get(0), SimpMessageHeaderAccessor.class);

		assertNotNull(headerAccessor);
		assertEquals("value", headerAccessor.toMap().get("key"));
		assertNull(headerAccessor.getNativeHeader("key"));
	}
