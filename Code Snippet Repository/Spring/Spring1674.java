	@Test
	public void testProps() throws Exception {
		HasMap hasMap = (HasMap) this.beanFactory.getBean("props");
		assertEquals(2, hasMap.getProps().size());
		assertEquals("bar", hasMap.getProps().getProperty("foo"));
		assertEquals("TWO", hasMap.getProps().getProperty("2"));

		HasMap hasMap2 = (HasMap) this.beanFactory.getBean("propsViaMap");
		assertEquals(2, hasMap2.getProps().size());
		assertEquals("bar", hasMap2.getProps().getProperty("foo"));
		assertEquals("TWO", hasMap2.getProps().getProperty("2"));
	}
