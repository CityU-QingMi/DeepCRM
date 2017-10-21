	@Test
	@SuppressWarnings("")
	public void valueFieldsAreNotProcessedWhenPlaceholderConfigurerIsIntegrated() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ConfigWithValueFieldAndPlaceholderConfigurer.class);
		System.setProperty("test.name", "foo");
		ctx.refresh();
		System.clearProperty("test.name");

		TestBean testBean = ctx.getBean(TestBean.class);
		// Proof that the @Value field did not get set:
		assertThat(testBean.getName(), nullValue());
	}
