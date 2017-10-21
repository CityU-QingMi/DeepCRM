	@Test
	public void genericApplicationContext_customEnv() {
		GenericApplicationContext ctx = new GenericApplicationContext(newBeanFactoryWithEnvironmentAwareBean());
		ctx.setEnvironment(prodEnv);
		ctx.refresh();

		assertHasEnvironment(ctx, prodEnv);
		assertEnvironmentBeanRegistered(ctx);
		assertEnvironmentAwareInvoked(ctx, prodEnv);
	}
