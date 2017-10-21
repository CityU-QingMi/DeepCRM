	@Test
	@SuppressWarnings("")
	public void mapClone() {
		map.put("key", "value1");
		LinkedCaseInsensitiveMap<String> copy = map.clone();

		assertEquals(map.getLocale(), copy.getLocale());
		assertEquals("value1", map.get("key"));
		assertEquals("value1", map.get("KEY"));
		assertEquals("value1", map.get("Key"));
		assertEquals("value1", copy.get("key"));
		assertEquals("value1", copy.get("KEY"));
		assertEquals("value1", copy.get("Key"));

		copy.put("Key", "value2");
		assertEquals(1, map.size());
		assertEquals(1, copy.size());
		assertEquals("value1", map.get("key"));
		assertEquals("value1", map.get("KEY"));
		assertEquals("value1", map.get("Key"));
		assertEquals("value2", copy.get("key"));
		assertEquals("value2", copy.get("KEY"));
		assertEquals("value2", copy.get("Key"));
	}
