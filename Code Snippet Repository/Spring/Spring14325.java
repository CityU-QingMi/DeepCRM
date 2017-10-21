	@Test
	public void classPathBeanDefinitionScanner_inheritsEnvironmentFromEnvironmentCapableBDR_scanProfileAnnotatedComponents() {
		GenericApplicationContext ctx = new GenericApplicationContext();
		ctx.setEnvironment(prodEnv);
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(ctx);
		scanner.scan("org.springframework.core.env.scan2");
		ctx.refresh();
		assertThat(scanner.getEnvironment(), is((Environment)ctx.getEnvironment()));
		assertThat(ctx.containsBean(DEV_BEAN_NAME), is(false));
		assertThat(ctx.containsBean(PROD_BEAN_NAME), is(true));
	}
