	@Test
	public void putAndGet() {
		map.put("key", "value1");
		map.put("key", "value2");
		map.put("key", "value3");
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
