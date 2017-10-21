	@Test(expected = IllegalStateException.class)
	public void multipleCachingConfigurers() throws Throwable {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MultiCacheManagerConfigurer.class, EnableCachingConfig.class);
		try {
			ctx.refresh();
		}
		catch (BeanCreationException ex) {
			Throwable root = ex.getRootCause();
			assertTrue(root.getMessage().contains("implementations of CachingConfigurer"));
			throw root;
		}
	}
