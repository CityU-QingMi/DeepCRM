	@Test
	public void propertyPlaceholderLocationWithSystemPropertyForMultipleLocations() throws Exception {
		System.setProperty("properties",
				"classpath*:/org/springframework/context/config/test-*.properties," +
				"classpath*:/org/springframework/context/config/empty-*.properties," +
				"classpath*:/org/springframework/context/config/missing-*.properties");
		try {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					"contextNamespaceHandlerTests-location-placeholder.xml", getClass());
			assertEquals("bar", applicationContext.getBean("foo"));
			assertEquals("foo", applicationContext.getBean("bar"));
			assertEquals("maps", applicationContext.getBean("spam"));
		}
		finally {
			System.clearProperty("properties");
		}
	}
