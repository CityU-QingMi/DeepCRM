	@Test
	public void convertAndSendPayloadAndMutableHeadersToDestination() {
		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		accessor.setHeader("foo", "bar");
		accessor.setLeaveMutable(true);
		MessageHeaders messageHeaders = accessor.getMessageHeaders();

		this.template.setMessageConverter(new StringMessageConverter());
		this.template.convertAndSend("somewhere", "payload", messageHeaders);

		MessageHeaders actual = this.template.message.getHeaders();
		assertSame(messageHeaders, actual);
		assertEquals(new MimeType("text", "plain", StandardCharsets.UTF_8), actual.get(MessageHeaders.CONTENT_TYPE));
		assertEquals("bar", actual.get("foo"));
	}
