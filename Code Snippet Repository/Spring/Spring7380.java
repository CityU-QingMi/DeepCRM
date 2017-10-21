	@Test
	public void toMessageUtf16() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		MimeType contentType = new MimeType("application", "json", StandardCharsets.UTF_16BE);
		Map<String, Object> map = new HashMap<>();
		map.put(MessageHeaders.CONTENT_TYPE, contentType);
		MessageHeaders headers = new MessageHeaders(map);
		String payload = "H\u00e9llo W\u00f6rld";
		Message<?> message = converter.toMessage(payload, headers);

		assertEquals("\"" + payload + "\"", new String((byte[]) message.getPayload(), StandardCharsets.UTF_16BE));
		assertEquals(contentType, message.getHeaders().get(MessageHeaders.CONTENT_TYPE));
	}
