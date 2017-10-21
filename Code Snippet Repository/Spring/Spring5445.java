	@Test
	public void add() {
		map.add("key", "value1");
		map.add("key", "value2");
		assertEquals(1, map.size());
		List<String> expected = new ArrayList<>(2);
		expected.add("value1");
		expected.add("value2");
		assertEquals(expected, map.get("key"));
	}
