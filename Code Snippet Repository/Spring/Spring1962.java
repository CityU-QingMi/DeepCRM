	@Test
	public void customKeyGeneratorSpringBean() {
		TestableCacheKeyGenerator bean = new TestableCacheKeyGenerator();
		beanFactory.registerSingleton("fooBar", bean);
		CacheResultOperation operation =
				getCacheOperation(CacheResultOperation.class, CustomService.class, name.getMethodName(), Long.class);
		assertEquals(defaultCacheResolver, operation.getCacheResolver());
		assertNull(operation.getExceptionCacheResolver());
		KeyGeneratorAdapter adapter = (KeyGeneratorAdapter) operation.getKeyGenerator();
		assertSame(bean, adapter.getTarget()); // take bean from context
	}
