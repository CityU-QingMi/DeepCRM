	@Test
	public void combineWithExpressionLanguage() {
		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("simplePropertyNamespaceHandlerWithExpressionLanguageTests.xml",
						getClass());
		ITestBean foo = applicationContext.getBean("foo", ITestBean.class);
		ITestBean bar = applicationContext.getBean("bar", ITestBean.class);
		assertEquals("Invalid name", "Baz", foo.getName());
		assertEquals("Invalid name", "Baz", bar.getName());
	}
