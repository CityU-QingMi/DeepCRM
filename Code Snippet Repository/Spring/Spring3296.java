	@Test
	public void beanMethods() {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		WithExplicitDestroyMethod c0 = ctx.getBean(WithExplicitDestroyMethod.class);
		WithLocalCloseMethod c1 = ctx.getBean("c1", WithLocalCloseMethod.class);
		WithLocalCloseMethod c2 = ctx.getBean("c2", WithLocalCloseMethod.class);
		WithInheritedCloseMethod c3 = ctx.getBean("c3", WithInheritedCloseMethod.class);
		WithInheritedCloseMethod c4 = ctx.getBean("c4", WithInheritedCloseMethod.class);
		WithInheritedCloseMethod c5 = ctx.getBean("c5", WithInheritedCloseMethod.class);
		WithNoCloseMethod c6 = ctx.getBean("c6", WithNoCloseMethod.class);
		WithLocalShutdownMethod c7 = ctx.getBean("c7", WithLocalShutdownMethod.class);
		WithInheritedCloseMethod c8 = ctx.getBean("c8", WithInheritedCloseMethod.class);

		assertThat(c0.closed, is(false));
		assertThat(c1.closed, is(false));
		assertThat(c2.closed, is(false));
		assertThat(c3.closed, is(false));
		assertThat(c4.closed, is(false));
		assertThat(c5.closed, is(false));
		assertThat(c6.closed, is(false));
		assertThat(c7.closed, is(false));
		assertThat(c8.closed, is(false));
		ctx.close();
		assertThat("c0", c0.closed, is(true));
		assertThat("c1", c1.closed, is(true));
		assertThat("c2", c2.closed, is(true));
		assertThat("c3", c3.closed, is(true));
		assertThat("c4", c4.closed, is(true));
		assertThat("c5", c5.closed, is(true));
		assertThat("c6", c6.closed, is(false));
		assertThat("c7", c7.closed, is(true));
		assertThat("c8", c8.closed, is(false));
	}
