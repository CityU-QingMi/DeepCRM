	@Test
	public void getOrDefault() {
		map.put("key", "value1");
		map.put("KEY", "value2");
		map.put("Key", "value3");
		assertEquals("value3", map.getOrDefault("key", "N"));
		assertEquals("value3", map.getOrDefault("KEY", "N"));
		assertEquals("value3", map.getOrDefault("Key", "N"));
		assertEquals("N", map.getOrDefault("keeeey", "N"));
		assertEquals("N", map.getOrDefault(new Object(), "N"));
	}
