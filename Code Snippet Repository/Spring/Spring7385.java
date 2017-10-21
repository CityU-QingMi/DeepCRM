	@Test
	public void toMessageWithHeaders() {
		Map<String, Object> map = new HashMap<>();
		map.put("foo", "bar");
		MessageHeaders headers = new MessageHeaders(map);
		Message<?> message = this.converter.toMessage("ABC", headers);

		assertNotNull(message.getHeaders().getId());
		assertNotNull(message.getHeaders().getTimestamp());
		assertEquals(MimeTypeUtils.TEXT_PLAIN, message.getHeaders().get(MessageHeaders.CONTENT_TYPE));
		assertEquals("bar", message.getHeaders().get("foo"));
	}
