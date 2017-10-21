	@Test
	public void annotationConfigApplicationContext_withDevEnvAndDevConfigClass() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		assertHasStandardEnvironment(ctx);
		ctx.setEnvironment(devEnv);

		ctx.register(DevConfig.class);
		ctx.refresh();

		assertThat("should have dev bean", ctx.containsBean(DEV_BEAN_NAME), is(true));
		assertThat("should have transitive bean", ctx.containsBean(TRANSITIVE_BEAN_NAME), is(true));
	}
