	@Test
	@SuppressWarnings("")
	public void valueFieldsAreProcessedWhenStaticPlaceholderConfigurerIsIntegrated() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ConfigWithValueFieldAndStaticPlaceholderConfigurer.class);
		System.setProperty("test.name", "foo");
		ctx.refresh();
		System.clearProperty("test.name");

		TestBean testBean = ctx.getBean(TestBean.class);
		assertThat(testBean.getName(), equalTo("foo"));
	}
