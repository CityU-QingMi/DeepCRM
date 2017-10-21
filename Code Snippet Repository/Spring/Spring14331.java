	@Test
	public void annotationConfigApplicationContext_withProdEnvAndDevConfigClass() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		assertHasStandardEnvironment(ctx);
		ctx.setEnvironment(prodEnv);

		ctx.register(DevConfig.class);
		ctx.refresh();

		assertThat("should not have dev bean", ctx.containsBean(DEV_BEAN_NAME), is(false));
		assertThat("should not have transitive bean", ctx.containsBean(TRANSITIVE_BEAN_NAME), is(false));
	}
