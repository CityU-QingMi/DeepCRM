	@Test
	public void testAutowireCandidatePatternDoesNotMatch() {
		GenericApplicationContext context = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.setIncludeAnnotationConfig(true);
		scanner.setBeanNameGenerator(new TestBeanNameGenerator());
		scanner.setAutowireCandidatePatterns("*NoSuchDao");
		scanner.scan(BASE_PACKAGE);

		try {
			context.refresh();
			context.getBean("fooService");
			fail("BeanCreationException expected; fooDao should not have been an autowire-candidate");
		}
		catch (BeanCreationException expected) {
			assertTrue(expected.getMostSpecificCause() instanceof NoSuchBeanDefinitionException);
		}
	}
