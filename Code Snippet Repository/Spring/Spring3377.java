	@Test
	public void repro() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ReproConfig.class);
		ctx.refresh();
		Foo foo1 = ctx.getBean("foo1", Foo.class);
		Foo foo2 = ctx.getBean("foo2", Foo.class);
		ctx.getBean("packagePrivateBar", Bar.class); // <-- i.e. @Bean was registered
		assertThat(foo1.bar, not(is(foo2.bar)));     // <-- i.e. @Bean *not* enhanced
	}
