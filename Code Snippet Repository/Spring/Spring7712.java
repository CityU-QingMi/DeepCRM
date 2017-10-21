	@Test
	public void copyHeadersIfAbsent() {
		Map<String, Object> map1 = new HashMap<>();
		map1.put("foo", "bar");
		GenericMessage<String> message = new GenericMessage<>("payload", map1);
		MessageHeaderAccessor accessor = new MessageHeaderAccessor(message);

		Map<String, Object> map2 = new HashMap<>();
		map2.put("foo", "BAR");
		map2.put("bar", "baz");
		accessor.copyHeadersIfAbsent(map2);

		MessageHeaders actual = accessor.getMessageHeaders();
		assertEquals(3, actual.size());
		assertEquals("bar", actual.get("foo"));
		assertEquals("baz", actual.get("bar"));
	}
