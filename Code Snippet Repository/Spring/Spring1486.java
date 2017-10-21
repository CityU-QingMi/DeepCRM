	@Test
	public void testMapWithIntegerValue() throws Exception {
		this.factory.setResources(new ByteArrayResource("foo:\n  ? key1.key2\n  : 3".getBytes()));
		Map<String, Object> map = this.factory.getObject();

		assertEquals(1, map.size());
		assertTrue(map.containsKey("foo"));
		Object object = map.get("foo");
		assertTrue(object instanceof LinkedHashMap);
		@SuppressWarnings("unchecked")
		Map<String, Object> sub = (Map<String, Object>) object;
		assertEquals(1, sub.size());
		assertEquals(Integer.valueOf(3), sub.get("key1.key2"));
	}
