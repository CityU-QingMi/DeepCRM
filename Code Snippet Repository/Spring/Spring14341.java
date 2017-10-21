	@Test
	public void repositoryIsNotTxProxy() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Config.class);
		ctx.refresh();

		try {
			assertTxProxying(ctx);
			fail("expected exception");
		}
		catch (AssertionError ex) {
			assertThat(ex.getMessage(), equalTo("FooRepository is not a TX proxy"));
		}
	}
