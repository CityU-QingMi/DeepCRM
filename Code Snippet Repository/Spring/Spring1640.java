	@Test
	public void testFactoryMethodWithDifferentReturnType() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.loadBeanDefinitions(new ClassPathResource("factory-methods.xml", getClass()));

		// Check that listInstance is not considered a bean of type FactoryMethods.
		assertTrue(List.class.isAssignableFrom(xbf.getType("listInstance")));
		String[] names = xbf.getBeanNamesForType(FactoryMethods.class);
		assertTrue(!Arrays.asList(names).contains("listInstance"));
		names = xbf.getBeanNamesForType(List.class);
		assertTrue(Arrays.asList(names).contains("listInstance"));

		xbf.preInstantiateSingletons();
		assertTrue(List.class.isAssignableFrom(xbf.getType("listInstance")));
		names = xbf.getBeanNamesForType(FactoryMethods.class);
		assertTrue(!Arrays.asList(names).contains("listInstance"));
		names = xbf.getBeanNamesForType(List.class);
		assertTrue(Arrays.asList(names).contains("listInstance"));
		List<?> list = (List<?>) xbf.getBean("listInstance");
		assertEquals(Collections.EMPTY_LIST, list);
	}
