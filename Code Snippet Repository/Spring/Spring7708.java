	@Test
	public void serializeMutableHeaders() throws Exception {
		Map<String, Object> headers = new HashMap<>();
		headers.put("foo", "bar");
		Message<String> message = new GenericMessage<>("test", headers);
		MessageHeaderAccessor mutableAccessor = MessageHeaderAccessor.getMutableAccessor(message);
		mutableAccessor.setContentType(MimeTypeUtils.TEXT_PLAIN);

		message = new GenericMessage<>(message.getPayload(), mutableAccessor.getMessageHeaders());
		Message<?> output = (Message<?>) SerializationTestUtils.serializeAndDeserialize(message);
		assertEquals("test", output.getPayload());
		assertEquals("bar", output.getHeaders().get("foo"));
		assertNotNull(output.getHeaders().get(MessageHeaders.CONTENT_TYPE));
	}
