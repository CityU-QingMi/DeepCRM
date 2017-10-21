	@Test
	public void aspectjTypeFilterWithPlaceholders() {
		System.setProperty("basePackage", "example.scannable, test");
		System.setProperty("scanInclude", "example.scannable.FooService+");
		System.setProperty("scanExclude", "example..Scoped*Test*");
		try {
			ClassPathXmlApplicationContext context = loadContext("aspectjTypeFilterTestsWithPlaceholders.xml");
			assertTrue(context.containsBean("fooServiceImpl"));
			assertTrue(context.containsBean("stubFooDao"));
			assertFalse(context.containsBean("scopedProxyTestBean"));
			context.close();
		}
		finally {
			System.clearProperty("basePackage");
			System.clearProperty("scanInclude");
			System.clearProperty("scanExclude");
		}
	}
