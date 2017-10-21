	@Test
	public void exceptionCacheResolverLazilyRequired() {
		ConfigurableApplicationContext context =
				new AnnotationConfigApplicationContext(NoExceptionCacheResolverConfig.class);

		try {
			DefaultJCacheOperationSource cos = context.getBean(DefaultJCacheOperationSource.class);
			assertSame(context.getBean("cacheResolver"), cos.getCacheResolver());

			JCacheableService<?> service = context.getBean(JCacheableService.class);
			service.cache("id");

			// This call requires the cache manager to be set
			thrown.expect(IllegalStateException.class);
			service.cacheWithException("test", false);
		}
		finally {
			context.close();
		}
	}
