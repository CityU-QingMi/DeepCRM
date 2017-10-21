	@Test
	public void genericXmlApplicationContext() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		assertHasStandardEnvironment(ctx);
		ctx.setEnvironment(prodEnv);
		ctx.load(XML_PATH);
		ctx.refresh();

		assertHasEnvironment(ctx, prodEnv);
		assertEnvironmentBeanRegistered(ctx);
		assertEnvironmentAwareInvoked(ctx, prodEnv);
		assertThat(ctx.containsBean(DEV_BEAN_NAME), is(false));
		assertThat(ctx.containsBean(PROD_BEAN_NAME), is(true));
	}
