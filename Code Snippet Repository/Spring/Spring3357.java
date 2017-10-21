	@Test
	public void registerNestedConfig() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(A.B.class);
		ctx.refresh();
		assertThat(ctx.containsBean("outer"), is(false));
		assertThat(ctx.containsBean("imported"), is(false));
		assertThat(ctx.containsBean("nested"), is(true));
		assertThat(ctx.containsBean("nestedBean"), is(true));
	}
