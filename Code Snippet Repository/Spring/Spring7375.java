	@Test
	public void serializeWithNonSerializableHeader() throws Exception {
		Object address = new Object();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "joe");
		map.put("address", address);
		MessageHeaders input = new MessageHeaders(map);
		MessageHeaders output = (MessageHeaders) SerializationTestUtils.serializeAndDeserialize(input);
		assertEquals("joe", output.get("name"));
		assertNull(output.get("address"));
		assertEquals("joe", input.get("name"));
		assertSame(address, input.get("address"));
	}
