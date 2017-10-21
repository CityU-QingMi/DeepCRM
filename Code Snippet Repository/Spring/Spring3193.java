	@Test
	public void testSimpleScanWithDefaultFiltersAndSpecifiedBeanNameClash() {
		GenericApplicationContext context = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.setIncludeAnnotationConfig(false);
		try {
			scanner.scan("org.springframework.context.annotation2");
			scanner.scan(BASE_PACKAGE);
			fail("Must have thrown IllegalStateException");
		}
		catch (IllegalStateException expected) {
			assertTrue(expected.getMessage().contains("myNamedDao"));
			assertTrue(expected.getMessage().contains(NamedStubDao.class.getName()));
			assertTrue(expected.getMessage().contains(NamedStubDao2.class.getName()));
		}
	}
