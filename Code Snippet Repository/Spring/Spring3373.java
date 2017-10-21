	@Test
	public void customConfigurationStereotype() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Config.class);
		ctx.refresh();
		assertThat(ctx.containsBean("customName"), is(true));
		TestBean a = ctx.getBean("a", TestBean.class);
		TestBean b = ctx.getBean("b", TestBean.class);
		assertThat(b, sameInstance(a.getSpouse()));
	}
