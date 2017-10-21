	@Test
	public void failsWhenJdkProxyAndScheduledMethodNotPresentOnInterface() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Config.class, JdkProxyTxConfig.class, RepoConfigA.class);
		try {
			ctx.refresh();
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getRootCause() instanceof IllegalStateException);
		}
	}
