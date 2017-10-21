	@Test
	public void annotationConfigApplicationContext_withImportedConfigClasses() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		assertHasStandardEnvironment(ctx);
		ctx.setEnvironment(prodEnv);

		ctx.register(Config.class);
		ctx.refresh();

		assertEnvironmentAwareInvoked(ctx, prodEnv);
		assertThat("should have prod bean", ctx.containsBean(PROD_BEAN_NAME), is(true));
		assertThat("should not have dev bean", ctx.containsBean(DEV_BEAN_NAME), is(false));
		assertThat("should not have transitive bean", ctx.containsBean(TRANSITIVE_BEAN_NAME), is(false));
	}
