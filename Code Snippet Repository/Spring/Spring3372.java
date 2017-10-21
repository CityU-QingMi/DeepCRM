	@Test
	@SuppressWarnings("")
	public void valueFieldsResolveToPlaceholderSpecifiedDefaultValuesWithoutPlaceholderConfigurer() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ConfigWithValueField.class);
		// ctx.register(ConfigWithPlaceholderConfigurer.class);
		ctx.refresh();

		TestBean testBean = ctx.getBean(TestBean.class);
		assertThat(testBean.getName(), equalTo("bar"));
	}
