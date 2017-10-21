	@Test
	public void existingHeaders() throws InterruptedException {
		Map<String, Object> map = new HashMap<>();
		map.put("foo", "bar");
		map.put("bar", "baz");
		GenericMessage<String> message = new GenericMessage<>("payload", map);

		MessageHeaderAccessor accessor = new MessageHeaderAccessor(message);
		MessageHeaders actual = accessor.getMessageHeaders();

		assertEquals(3, actual.size());
		assertEquals("bar", actual.get("foo"));
		assertEquals("baz", actual.get("bar"));
	}
