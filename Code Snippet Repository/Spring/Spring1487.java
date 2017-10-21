	@Test
	public void testDuplicateKey() throws Exception {
		this.factory.setResources(new ByteArrayResource("mymap:\n  foo: bar\nmymap:\n  bar: foo".getBytes()));
		Map<String, Object> map = this.factory.getObject();

		assertEquals(1, map.size());
		assertTrue(map.containsKey("mymap"));
		Object object = map.get("mymap");
		assertTrue(object instanceof LinkedHashMap);
		@SuppressWarnings("unchecked")
		Map<String, Object> sub = (Map<String, Object>) object;
		assertEquals(1, sub.size());
		assertEquals("foo", sub.get("bar"));
	}
