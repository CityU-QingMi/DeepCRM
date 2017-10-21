	@Test
	public void equals() {
		map.set("key1", "value1");
		assertEquals(map, map);
		MultiValueMap<String, String> o1 = new LinkedMultiValueMap<>();
		o1.set("key1", "value1");
		assertEquals(map, o1);
		assertEquals(o1, map);
		Map<String, List<String>> o2 = new HashMap<>();
		o2.put("key1", Collections.singletonList("value1"));
		assertEquals(map, o2);
		assertEquals(o2, map);
	}
