	@Test
	public void mostSpecificDerivedClassDrivesEnvironment_withDerivedDevEnvAndDerivedDevConfigClass() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		StandardEnvironment derivedDevEnv = new StandardEnvironment();
		derivedDevEnv.setActiveProfiles(DERIVED_DEV_ENV_NAME);
		ctx.setEnvironment(derivedDevEnv);
		ctx.register(DerivedDevConfig.class);
		ctx.refresh();

		assertThat("should have dev bean", ctx.containsBean(DEV_BEAN_NAME), is(true));
		assertThat("should have derived dev bean", ctx.containsBean(DERIVED_DEV_BEAN_NAME), is(true));
		assertThat("should have transitive bean", ctx.containsBean(TRANSITIVE_BEAN_NAME), is(true));
	}
