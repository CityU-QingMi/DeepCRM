	@Test(expected = IllegalStateException.class)
	public void noCacheManagerBeans() throws Throwable {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(EmptyConfig.class);
		try {
			ctx.refresh();
		}
		catch (BeanCreationException ex) {
			Throwable root = ex.getRootCause();
			assertTrue(root.getMessage().contains("No bean of type CacheManager"));
			throw root;
		}
	}
