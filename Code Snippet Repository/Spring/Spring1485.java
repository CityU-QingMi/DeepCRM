	@Test
	public void testMapWithPeriodsInKey() throws Exception {
		this.factory.setResources(new ByteArrayResource("foo:\n  ? key1.key2\n  : value".getBytes()));
		Map<String, Object> map = this.factory.getObject();

		assertEquals(1, map.size());
		assertTrue(map.containsKey("foo"));
		Object object = map.get("foo");
		assertTrue(object instanceof LinkedHashMap);
		@SuppressWarnings("unchecked")
		Map<String, Object> sub = (Map<String, Object>) object;
		assertTrue(sub.containsKey("key1.key2"));
		assertEquals("value", sub.get("key1.key2"));
	}
