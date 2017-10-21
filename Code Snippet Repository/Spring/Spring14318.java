	@Test
	public void annotationConfigWebApplicationContext() {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.setEnvironment(prodWebEnv);
		ctx.setConfigLocation(EnvironmentAwareBean.class.getName());
		ctx.refresh();

		assertHasEnvironment(ctx, prodWebEnv);
		assertEnvironmentBeanRegistered(ctx);
		assertEnvironmentAwareInvoked(ctx, prodWebEnv);
	}
