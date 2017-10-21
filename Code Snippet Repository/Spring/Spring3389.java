	@Test
	public void propertyPlaceholderSystemProperties() throws Exception {
		String value = System.setProperty("foo", "spam");
		try {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					"contextNamespaceHandlerTests-system.xml", getClass());
			assertEquals("spam", applicationContext.getBean("string"));
			assertEquals("none", applicationContext.getBean("fallback"));
		}
		finally {
			if (value != null) {
				System.setProperty("foo", value);
			}
		}
	}
