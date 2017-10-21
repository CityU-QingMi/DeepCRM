	@Test
	public void propertyPlaceholderLocationWithSystemPropertyMissing() throws Exception {
		try {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					"contextNamespaceHandlerTests-location-placeholder.xml", getClass());
			assertEquals("bar", applicationContext.getBean("foo"));
			assertEquals("foo", applicationContext.getBean("bar"));
			assertEquals("maps", applicationContext.getBean("spam"));
		}
		catch (FatalBeanException ex) {
			assertTrue(ex.getRootCause() instanceof FileNotFoundException);
		}
	}
