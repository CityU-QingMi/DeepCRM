	@Test
	public void twoLevelsDeep() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(L0Config.class);
		ctx.refresh();

		assertFalse(ctx.getBeanFactory().containsSingleton("nestedConfigurationClassTests.L0Config"));
		ctx.getBean(L0Config.class);
		ctx.getBean("l0Bean");

		assertTrue(ctx.getBeanFactory().containsSingleton(L0Config.L1Config.class.getName()));
		ctx.getBean(L0Config.L1Config.class);
		ctx.getBean("l1Bean");

		assertFalse(ctx.getBeanFactory().containsSingleton(L0Config.L1Config.L2Config.class.getName()));
		ctx.getBean(L0Config.L1Config.L2Config.class);
		ctx.getBean("l2Bean");

		// ensure that override order is correct
		assertThat(ctx.getBean("overrideBean", TestBean.class).getName(), is("override-l0"));
	}
