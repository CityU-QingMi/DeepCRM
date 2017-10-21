	@Test
	public void workaround() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(WorkaroundConfig.class);
		ctx.refresh();
		Foo foo1 = ctx.getBean("foo1", Foo.class);
		Foo foo2 = ctx.getBean("foo2", Foo.class);
		ctx.getBean("protectedBar", Bar.class); // <-- i.e. @Bean was registered
		assertThat(foo1.bar, is(foo2.bar));     // <-- i.e. @Bean *was* enhanced
	}
