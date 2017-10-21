	@Test
	public void fullCachingConfig() throws Exception {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(FullCachingConfig.class);

		DefaultJCacheOperationSource cos = context.getBean(DefaultJCacheOperationSource.class);
		assertSame(context.getBean(KeyGenerator.class), cos.getKeyGenerator());
		assertSame(context.getBean("cacheResolver", CacheResolver.class),
				cos.getCacheResolver());
		assertSame(context.getBean("exceptionCacheResolver", CacheResolver.class),
				cos.getExceptionCacheResolver());
		JCacheInterceptor interceptor = context.getBean(JCacheInterceptor.class);
		assertSame(context.getBean("errorHandler", CacheErrorHandler.class), interceptor.getErrorHandler());
	}
