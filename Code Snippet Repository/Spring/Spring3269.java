	@Test
	public void genericsBasedInjectionWithLateGenericsMatching() {
		beanFactory.registerBeanDefinition("configClass", new RootBeanDefinition(RepositoryConfiguration.class));
		new ConfigurationClassPostProcessor().postProcessBeanFactory(beanFactory);
		beanFactory.preInstantiateSingletons();

		String[] beanNames = beanFactory.getBeanNamesForType(Repository.class);
		assertTrue(ObjectUtils.containsElement(beanNames, "stringRepo"));

		beanNames = beanFactory.getBeanNamesForType(ResolvableType.forClassWithGenerics(Repository.class, String.class));
		assertEquals(1, beanNames.length);
		assertEquals("stringRepo", beanNames[0]);
	}
