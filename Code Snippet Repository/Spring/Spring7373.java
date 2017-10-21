	@Test
	public void testHeaderKeys() {
		Map<String, Object> map = new HashMap<>();
		map.put("key1", "val1");
		map.put("key2", new Integer(123));
		MessageHeaders headers = new MessageHeaders(map);
		Set<String> keys = headers.keySet();
		assertTrue(keys.contains("key1"));
		assertTrue(keys.contains("key2"));
	}
