	@Test
	public void multipleCachingConfigurers() {
		try {
			load(MultiCacheManagerConfigurer.class, EnableCachingConfig.class);
		}
		catch (BeanCreationException ex) {
			Throwable root = ex.getRootCause();
			assertTrue(root instanceof IllegalStateException);
			assertTrue(ex.getMessage().contains("implementations of CachingConfigurer"));
		}
	}
