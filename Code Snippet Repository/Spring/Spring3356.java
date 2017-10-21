	@Test
	public void registerOuterConfig() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(A.class);
		ctx.refresh();
		assertThat(ctx.containsBean("outer"), is(true));
		assertThat(ctx.containsBean("imported"), is(true));
		assertThat(ctx.containsBean("nested"), is(true));
		assertThat(ctx.containsBean("nestedBean"), is(true));
	}
