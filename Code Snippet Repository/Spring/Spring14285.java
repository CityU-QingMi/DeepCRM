	@Test
	public void repositoryUsesAspectJAdviceMode() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(Config.class, AspectJCacheConfig.class);
		try {
			ctx.refresh();
		}
		catch (Exception ex) {
			// this test is a bit fragile, but gets the job done, proving that an
			// attempt was made to look up the AJ aspect. It's due to classpath issues
			// in .integration-tests that it's not found.
			assertTrue(ex.getMessage().contains("AspectJCachingConfiguration"));
		}
	}
