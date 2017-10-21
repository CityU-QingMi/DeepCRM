	@Test
	public void mostSpecificDerivedClassDrivesEnvironment_withDevEnvAndDerivedDevConfigClass() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.setEnvironment(devEnv);
		ctx.register(DerivedDevConfig.class);
		ctx.refresh();

		assertThat("should not have dev bean", ctx.containsBean(DEV_BEAN_NAME), is(false));
		assertThat("should not have derived dev bean", ctx.containsBean(DERIVED_DEV_BEAN_NAME), is(false));
		assertThat("should not have transitive bean", ctx.containsBean(TRANSITIVE_BEAN_NAME), is(false));
	}
