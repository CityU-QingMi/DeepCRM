	@Test
	public void convertAndSendWithCustomHeader() {
		Map<String, Object> headers = Collections.<String, Object>singletonMap("key", "value");
		this.messagingTemplate.convertAndSend("/foo", "data", headers);

		List<Message<byte[]>> messages = this.messageChannel.getMessages();

		SimpMessageHeaderAccessor headerAccessor =
				MessageHeaderAccessor.getAccessor(messages.get(0), SimpMessageHeaderAccessor.class);

		assertNotNull(headerAccessor);
		assertNull(headerAccessor.toMap().get("key"));
		assertEquals(Arrays.asList("value"), headerAccessor.getNativeHeader("key"));
	}
