	@Test
	public void addAll() throws Exception {
		map.add("key", "value1");
		map.addAll("key", Arrays.asList("value2", "value3"));
		assertEquals(1, map.size());
		List<String> expected = new ArrayList<>(2);
		expected.add("value1");
		expected.add("value2");
		expected.add("value3");
		assertEquals(expected, map.get("key"));
	}
