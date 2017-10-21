	@Test
	public void testMapWithLiteralsAndReferences() throws Exception {
		HasMap hasMap = (HasMap) this.beanFactory.getBean("mixedMap");
		assertTrue(hasMap.getMap().size() == 5);
		assertTrue(hasMap.getMap().get("foo").equals(new Integer(10)));
		TestBean jenny = (TestBean) this.beanFactory.getBean("jenny");
		assertTrue(hasMap.getMap().get("jenny") == jenny);
		assertTrue(hasMap.getMap().get(new Integer(5)).equals("david"));
		assertTrue(hasMap.getMap().get("bar") instanceof Long);
		assertTrue(hasMap.getMap().get("bar").equals(new Long(100)));
		assertTrue(hasMap.getMap().get("baz") instanceof Integer);
		assertTrue(hasMap.getMap().get("baz").equals(new Integer(200)));
	}
