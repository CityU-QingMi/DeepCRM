	@Test
	public void testPopulatedSet() throws Exception {
		HasMap hasMap = (HasMap) this.beanFactory.getBean("set");
		assertTrue(hasMap.getSet().size() == 3);
		assertTrue(hasMap.getSet().contains("bar"));
		TestBean jenny = (TestBean) this.beanFactory.getBean("jenny");
		assertTrue(hasMap.getSet().contains(jenny));
		assertTrue(hasMap.getSet().contains(null));
		Iterator it = hasMap.getSet().iterator();
		assertEquals("bar", it.next());
		assertEquals(jenny, it.next());
		assertEquals(null, it.next());
	}
