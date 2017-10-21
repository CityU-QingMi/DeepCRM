	@Test
	public void bothSetOnlyResolverIsUsed() {
		ConfigurableApplicationContext context =
				new AnnotationConfigApplicationContext(FullCachingConfigSupport.class);

		DefaultJCacheOperationSource cos = context.getBean(DefaultJCacheOperationSource.class);
		assertSame(context.getBean("cacheResolver"), cos.getCacheResolver());
		assertSame(context.getBean("keyGenerator"), cos.getKeyGenerator());
		assertSame(context.getBean("exceptionCacheResolver"), cos.getExceptionCacheResolver());
		context.close();
	}
