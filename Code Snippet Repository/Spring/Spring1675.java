	@Test
	public void testChoiceBetweenSetAndMap() {
		MapAndSet sam = (MapAndSet) this.beanFactory.getBean("setAndMap");
		assertTrue("Didn't choose constructor with Map argument", sam.getObject() instanceof Map);
		Map map = (Map) sam.getObject();
		assertEquals(3, map.size());
		assertEquals("val1", map.get("key1"));
		assertEquals("val2", map.get("key2"));
		assertEquals("val3", map.get("key3"));
	}
