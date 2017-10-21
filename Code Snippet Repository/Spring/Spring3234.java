	@Test
	public void recursion() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Level1Config.class);
		ctx.refresh();

		// assert that all levels have been detected
		ctx.getBean(Level1Config.class);
		ctx.getBean(Level2Config.class);
		ctx.getBean(Level3Component.class);

		// assert that enhancement is working
		assertThat(ctx.getBean("level1Bean"), sameInstance(ctx.getBean("level1Bean")));
		assertThat(ctx.getBean("level2Bean"), sameInstance(ctx.getBean("level2Bean")));
	}
