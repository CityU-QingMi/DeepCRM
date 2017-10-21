	@Test
	public void testGetBeanNamesForTypeWithOverride() throws Exception {
		List<String> names = Arrays.asList(
				BeanFactoryUtils.beanNamesForTypeIncludingAncestors(this.listableBeanFactory, ITestBean.class));
		// includes 2 TestBeans from FactoryBeans (DummyFactory definitions)
		assertEquals(4, names.size());
		assertTrue(names.contains("test"));
		assertTrue(names.contains("test3"));
		assertTrue(names.contains("testFactory1"));
		assertTrue(names.contains("testFactory2"));
	}
