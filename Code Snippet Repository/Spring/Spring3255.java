	@Test
	public void control() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Config1.class, Config2.class);
		ctx.refresh();

		assertions(ctx);

		Config2 config2 = ctx.getBean(Config2.class);
		assertThat(config2.testBean, is(ctx.getBean(TestBean.class)));
	}
