	@Test
	public void toMap() {
		MessageHeaderAccessor accessor = new MessageHeaderAccessor();

		accessor.setHeader("foo", "bar1");
		Map<String, Object> map1 = accessor.toMap();

		accessor.setHeader("foo", "bar2");
		Map<String, Object> map2 = accessor.toMap();

		accessor.setHeader("foo", "bar3");
		Map<String, Object> map3 = accessor.toMap();

		assertEquals(1, map1.size());
		assertEquals(1, map2.size());
		assertEquals(1, map3.size());

		assertEquals("bar1", map1.get("foo"));
		assertEquals("bar2", map2.get("foo"));
		assertEquals("bar3", map3.get("foo"));
	}
