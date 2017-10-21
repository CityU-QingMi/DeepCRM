	@Test
	public void xml() {
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext(
				getClass(), "DestroyMethodInferenceTests-context.xml");
		WithLocalCloseMethod x1 = ctx.getBean("x1", WithLocalCloseMethod.class);
		WithLocalCloseMethod x2 = ctx.getBean("x2", WithLocalCloseMethod.class);
		WithLocalCloseMethod x3 = ctx.getBean("x3", WithLocalCloseMethod.class);
		WithNoCloseMethod x4 = ctx.getBean("x4", WithNoCloseMethod.class);
		WithInheritedCloseMethod x8 = ctx.getBean("x8", WithInheritedCloseMethod.class);

		assertThat(x1.closed, is(false));
		assertThat(x2.closed, is(false));
		assertThat(x3.closed, is(false));
		assertThat(x4.closed, is(false));
		ctx.close();
		assertThat(x1.closed, is(false));
		assertThat(x2.closed, is(true));
		assertThat(x3.closed, is(true));
		assertThat(x4.closed, is(false));
		assertThat(x8.closed, is(false));
	}
