	@Test
	public void classPathXmlApplicationContext() {
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(XML_PATH);
		ctx.setEnvironment(prodEnv);
		ctx.refresh();

		assertEnvironmentBeanRegistered(ctx);
		assertHasEnvironment(ctx, prodEnv);
		assertEnvironmentAwareInvoked(ctx, ctx.getEnvironment());
		assertThat(ctx.containsBean(DEV_BEAN_NAME), is(false));
		assertThat(ctx.containsBean(PROD_BEAN_NAME), is(true));
	}
