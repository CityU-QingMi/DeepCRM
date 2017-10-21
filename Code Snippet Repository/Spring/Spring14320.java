	@Test
	public void classPathBeanDefinitionScanner_inheritsEnvironmentFromEnvironmentCapableBDR_scanProfileAnnotatedConfigClasses() {
		// it's actually ConfigurationClassPostProcessor's Environment that gets the job done here.
		GenericApplicationContext ctx = new GenericApplicationContext();
		ctx.setEnvironment(prodEnv);
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(ctx);
		scanner.scan("org.springframework.core.env.scan1");
		ctx.refresh();
		assertThat(ctx.containsBean(DEV_BEAN_NAME), is(false));
		assertThat(ctx.containsBean(PROD_BEAN_NAME), is(true));
	}
