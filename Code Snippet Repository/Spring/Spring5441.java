	@Test
	public void putWithOverlappingKeys() {
		map.put("key", "value1");
		map.put("KEY", "value2");
		map.put("Key", "value3");
		assertEquals(1, map.size());
		assertEquals("value3", map.get("key"));
		assertEquals("value3", map.get("KEY"));
		assertEquals("value3", map.get("Key"));
		assertTrue(map.containsKey("key"));
		assertTrue(map.containsKey("KEY"));
		assertTrue(map.containsKey("Key"));
		assertTrue(map.keySet().contains("key"));
		assertTrue(map.keySet().contains("KEY"));
		assertTrue(map.keySet().contains("Key"));
	}
