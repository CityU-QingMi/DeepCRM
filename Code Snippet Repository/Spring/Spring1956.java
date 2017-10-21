	@Test
	public void emptyConfigSupport() {
		ConfigurableApplicationContext context =
				new AnnotationConfigApplicationContext(EmptyConfigSupportConfig.class);

		DefaultJCacheOperationSource cos = context.getBean(DefaultJCacheOperationSource.class);
		assertNotNull(cos.getCacheResolver());
		assertEquals(SimpleCacheResolver.class, cos.getCacheResolver().getClass());
		assertSame(context.getBean(CacheManager.class),
				((SimpleCacheResolver) cos.getCacheResolver()).getCacheManager());
		assertNull(cos.getExceptionCacheResolver());
		context.close();
	}
