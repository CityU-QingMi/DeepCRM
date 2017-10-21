	@Test
	public void shouldInvokeAwareMethodsInImportBeanDefinitionRegistrar() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		context.getBean(MessageSource.class);

		assertThat(SampleRegistrar.beanFactory, is((BeanFactory) context.getBeanFactory()));
		assertThat(SampleRegistrar.classLoader, is(context.getBeanFactory().getBeanClassLoader()));
		assertThat(SampleRegistrar.resourceLoader, is(notNullValue()));
		assertThat(SampleRegistrar.environment, is((Environment) context.getEnvironment()));
	}
