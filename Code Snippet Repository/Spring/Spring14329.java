	@Test
	public void annotationConfigApplicationContext_withPojos() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		assertHasStandardEnvironment(ctx);
		ctx.setEnvironment(prodEnv);

		ctx.register(EnvironmentAwareBean.class);
		ctx.refresh();

		assertEnvironmentAwareInvoked(ctx, prodEnv);
	}
