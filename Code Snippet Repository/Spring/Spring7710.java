	@Test
	public void removeHeaders() {
		Map<String, Object> map = new HashMap<>();
		map.put("foo", "bar");
		map.put("bar", "baz");
		GenericMessage<String> message = new GenericMessage<>("payload", map);
		MessageHeaderAccessor accessor = new MessageHeaderAccessor(message);

		accessor.removeHeaders("fo*");

		MessageHeaders actual = accessor.getMessageHeaders();
		assertEquals(2, actual.size());
		assertNull(actual.get("foo"));
		assertEquals("baz", actual.get("bar"));
	}
