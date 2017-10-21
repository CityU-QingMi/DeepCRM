	@Test
	public void testBeanNotAutowiredWithAnnotationConfigDisabled() {
		GenericApplicationContext context = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.setIncludeAnnotationConfig(false);
		scanner.setBeanNameGenerator(new TestBeanNameGenerator());
		int beanCount = scanner.scan(BASE_PACKAGE);
		assertEquals(6, beanCount);
		context.refresh();

		try {
			context.getBean("fooService");
		}
		catch (BeanCreationException expected) {
			assertTrue(expected.contains(BeanInstantiationException.class));
			// @Lookup method not substituted
		}
	}
