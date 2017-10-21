	@Test
	public void existingHeadersModification() throws InterruptedException {
		Map<String, Object> map = new HashMap<>();
		map.put("foo", "bar");
		map.put("bar", "baz");
		GenericMessage<String> message = new GenericMessage<>("payload", map);

		Thread.sleep(50);

		MessageHeaderAccessor accessor = new MessageHeaderAccessor(message);
		accessor.setHeader("foo", "BAR");
		MessageHeaders actual = accessor.getMessageHeaders();

		assertEquals(3, actual.size());
		assertNotEquals(message.getHeaders().getId(), actual.getId());
		assertEquals("BAR", actual.get("foo"));
		assertEquals("baz", actual.get("bar"));
	}
