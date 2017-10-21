	@Test
	public void xmlWebApplicationContext() {
		AbstractRefreshableWebApplicationContext ctx = new XmlWebApplicationContext();
		ctx.setConfigLocation("classpath:" + XML_PATH);
		ctx.setEnvironment(prodWebEnv);
		ctx.refresh();

		assertHasEnvironment(ctx, prodWebEnv);
		assertEnvironmentBeanRegistered(ctx);
		assertEnvironmentAwareInvoked(ctx, prodWebEnv);
		assertThat(ctx.containsBean(DEV_BEAN_NAME), is(false));
		assertThat(ctx.containsBean(PROD_BEAN_NAME), is(true));
	}
