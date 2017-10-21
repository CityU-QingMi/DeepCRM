	@Test
	public void testSimpleScanWithDefaultFiltersAndDefaultBeanNameClash() {
		GenericApplicationContext context = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.setIncludeAnnotationConfig(false);
		try {
			scanner.scan("org.springframework.context.annotation3");
			scanner.scan(BASE_PACKAGE);
			fail("Should have thrown IllegalStateException");
		}
		catch (IllegalStateException ex) {
			// expected
			assertTrue(ex.getMessage().contains("stubFooDao"));
			assertTrue(ex.getMessage().contains(StubFooDao.class.getName()));
		}
	}
