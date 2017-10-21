	@Test
	public void serializeWithAllSerializableHeaders() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "joe");
		map.put("age", 42);
		MessageHeaders input = new MessageHeaders(map);
		MessageHeaders output = (MessageHeaders) SerializationTestUtils.serializeAndDeserialize(input);
		assertEquals("joe", output.get("name"));
		assertEquals(42, output.get("age"));
		assertEquals("joe", input.get("name"));
		assertEquals(42, input.get("age"));
	}
