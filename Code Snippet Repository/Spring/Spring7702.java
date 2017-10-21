	@Test
	public void testBuildMultipleMessages() {
		MessageHeaderAccessor headerAccessor = new MessageHeaderAccessor();
		MessageBuilder<?> messageBuilder = MessageBuilder.withPayload("payload").setHeaders(headerAccessor);

		headerAccessor.setHeader("foo", "bar1");
		Message<?> message1 = messageBuilder.build();

		headerAccessor.setHeader("foo", "bar2");
		Message<?> message2 = messageBuilder.build();

		headerAccessor.setHeader("foo", "bar3");
		Message<?> message3 = messageBuilder.build();

		assertEquals("bar1", message1.getHeaders().get("foo"));
		assertEquals("bar2", message2.getHeaders().get("foo"));
		assertEquals("bar3", message3.getHeaders().get("foo"));
	}
