	@Test
	public void testAutowireByType() {
		GenericApplicationContext context = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(LOCATION_PREFIX + "defaultAutowireByTypeTests.xml");
		try {
			context.refresh();
			fail("expected exception due to multiple matches for byType autowiring");
		}
		catch (UnsatisfiedDependencyException ex) {
			// expected
		}
	}
