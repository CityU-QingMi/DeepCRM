	@Test
	public void testAutowireCandidatePatternMatches() {
		GenericApplicationContext context = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.setIncludeAnnotationConfig(true);
		scanner.setBeanNameGenerator(new TestBeanNameGenerator());
		scanner.setAutowireCandidatePatterns("*FooDao");
		scanner.scan(BASE_PACKAGE);
		context.refresh();

		FooServiceImpl fooService = (FooServiceImpl) context.getBean("fooService");
		assertEquals("bar", fooService.foo(123));
		assertEquals("bar", fooService.lookupFoo(123));
	}
