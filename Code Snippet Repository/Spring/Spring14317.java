	@Test
	public void staticWebApplicationContext() {
		StaticWebApplicationContext ctx = new StaticWebApplicationContext();

		assertHasStandardServletEnvironment(ctx);

		registerEnvironmentBeanDefinition(ctx);

		ctx.setEnvironment(prodWebEnv);
		ctx.refresh();

		assertHasEnvironment(ctx, prodWebEnv);
		assertEnvironmentBeanRegistered(ctx);
		assertEnvironmentAwareInvoked(ctx, prodWebEnv);
	}
