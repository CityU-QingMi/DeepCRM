	@Test
	public void getOrDefaultWithNullValue() {
		map.put("key", null);
		map.put("KEY", null);
		map.put("Key", null);
		assertNull(map.getOrDefault("key", "N"));
		assertNull(map.getOrDefault("KEY", "N"));
		assertNull(map.getOrDefault("Key", "N"));
		assertEquals("N", map.getOrDefault("keeeey", "N"));
		assertEquals("N", map.getOrDefault(new Object(), "N"));
	}
