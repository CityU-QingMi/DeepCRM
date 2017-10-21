	@Test
	public void resourceAdapterApplicationContext() {
		ResourceAdapterApplicationContext ctx = new ResourceAdapterApplicationContext(new SimpleBootstrapContext(new SimpleTaskWorkManager()));

		assertHasStandardEnvironment(ctx);

		registerEnvironmentBeanDefinition(ctx);

		ctx.setEnvironment(prodEnv);
		ctx.refresh();

		assertHasEnvironment(ctx, prodEnv);
		assertEnvironmentBeanRegistered(ctx);
		assertEnvironmentAwareInvoked(ctx, prodEnv);
	}
