	@Test
	public void annotationConfigApplicationContext_withProdEnvAndProdConfigClass() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		assertHasStandardEnvironment(ctx);
		ctx.setEnvironment(prodEnv);

		ctx.register(ProdConfig.class);
		ctx.refresh();

		assertThat("should have prod bean", ctx.containsBean(PROD_BEAN_NAME), is(true));
	}
