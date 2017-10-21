	@Test
	public void oneLevelDeep() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(L0Config.L1Config.class);
		ctx.refresh();

		assertFalse(ctx.containsBean("l0Bean"));

		ctx.getBean(L0Config.L1Config.class);
		ctx.getBean("l1Bean");

		ctx.getBean(L0Config.L1Config.L2Config.class);
		ctx.getBean("l2Bean");

		// ensure that override order is correct
		assertThat(ctx.getBean("overrideBean", TestBean.class).getName(), is("override-l1"));
	}
