	@Test(expected = IllegalStateException.class)
	public void multipleCacheManagerBeans() throws Throwable {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MultiCacheManagerConfig.class);
		try {
			ctx.refresh();
		}
		catch (BeanCreationException ex) {
			Throwable root = ex.getRootCause();
			assertTrue(root.getMessage().contains("beans of type CacheManager"));
			throw root;
		}
	}
