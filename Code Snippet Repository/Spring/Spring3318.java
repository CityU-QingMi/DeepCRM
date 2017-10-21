	@Test
	public void twoLevelsDeepWithInheritance() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(S1Config.class);
		ctx.refresh();

		S1Config config = ctx.getBean(S1Config.class);
		assertTrue(config != ctx.getBean(S1Config.class));
		TestBean tb = ctx.getBean("l0Bean", TestBean.class);
		assertTrue(tb == ctx.getBean("l0Bean", TestBean.class));

		ctx.getBean(L0Config.L1Config.class);
		ctx.getBean("l1Bean");

		ctx.getBean(L0Config.L1Config.L2Config.class);
		ctx.getBean("l2Bean");

		// ensure that override order is correct and that it is a singleton
		TestBean ob = ctx.getBean("overrideBean", TestBean.class);
		assertThat(ob.getName(), is("override-s1"));
		assertTrue(ob == ctx.getBean("overrideBean", TestBean.class));

		TestBean pb1 = ctx.getBean("prototypeBean", TestBean.class);
		TestBean pb2 = ctx.getBean("prototypeBean", TestBean.class);
		assertTrue(pb1 != pb2);
		assertTrue(pb1.getFriends().iterator().next() != pb2.getFriends().iterator().next());
	}
