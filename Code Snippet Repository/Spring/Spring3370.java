	@Test
	@SuppressWarnings("")
	public void valueFieldsAreProcessedWhenPlaceholderConfigurerIsSegregated() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ConfigWithValueField.class);
		ctx.register(ConfigWithPlaceholderConfigurer.class);
		System.setProperty("test.name", "foo");
		ctx.refresh();
		System.clearProperty("test.name");

		TestBean testBean = ctx.getBean(TestBean.class);
		assertThat(testBean.getName(), equalTo("foo"));
	}
