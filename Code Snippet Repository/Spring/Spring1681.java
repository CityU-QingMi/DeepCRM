	@Test
	public void testMapWithLiteralsAndPrototypeReferences() throws Exception {
		TestBean jenny = (TestBean) this.beanFactory.getBean("pJenny");
		HasMap hasMap = (HasMap) this.beanFactory.getBean("pMixedMap");
		assertTrue(hasMap.getMap().size() == 2);
		assertTrue(hasMap.getMap().get("foo").equals("bar"));
		assertTrue(hasMap.getMap().get("jenny").toString().equals(jenny.toString()));
		assertTrue("Not same instance", hasMap.getMap().get("jenny") != jenny);

		HasMap hasMap2 = (HasMap) this.beanFactory.getBean("pMixedMap");
		assertTrue(hasMap2.getMap().size() == 2);
		assertTrue(hasMap2.getMap().get("foo").equals("bar"));
		assertTrue(hasMap2.getMap().get("jenny").toString().equals(jenny.toString()));
		assertTrue("Not same instance", hasMap2.getMap().get("jenny") != hasMap.getMap().get("jenny"));
	}
