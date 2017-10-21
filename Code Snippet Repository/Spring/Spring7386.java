	@Test
	public void toMessageWithMutableMessageHeaders() {
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		accessor.setHeader("foo", "bar");
		accessor.setNativeHeader("fooNative", "barNative");
		accessor.setLeaveMutable(true);

		MessageHeaders headers = accessor.getMessageHeaders();
		Message<?> message = this.converter.toMessage("ABC", headers);

		assertSame(headers, message.getHeaders());
		assertNull(message.getHeaders().getId());
		assertNull(message.getHeaders().getTimestamp());
		assertEquals(MimeTypeUtils.TEXT_PLAIN, message.getHeaders().get(MessageHeaders.CONTENT_TYPE));
	}
