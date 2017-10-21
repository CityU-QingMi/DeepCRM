	@Test
	public void webApplicationContext() {
		GenericWebApplicationContext ctx = new GenericWebApplicationContext(newBeanFactoryWithEnvironmentAwareBean());
		assertHasStandardServletEnvironment(ctx);
		ctx.setEnvironment(prodWebEnv);
		ctx.refresh();

		assertHasEnvironment(ctx, prodWebEnv);
		assertEnvironmentBeanRegistered(ctx);
		assertEnvironmentAwareInvoked(ctx, prodWebEnv);
	}
