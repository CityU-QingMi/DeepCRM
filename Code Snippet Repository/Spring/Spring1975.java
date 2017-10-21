	@Test
	public void cacheResultReturnsProperType() throws Throwable {
		JCacheInterceptor interceptor = createInterceptor(createOperationSource(
				cacheManager, defaultCacheResolver, defaultExceptionCacheResolver, defaultKeyGenerator));

		AnnotatedJCacheableService service = new AnnotatedJCacheableService(cacheManager.getCache("default"));
		Method method = ReflectionUtils.findMethod(AnnotatedJCacheableService.class, "cache", String.class);

		CacheOperationInvoker invoker = new DummyInvoker(0L);
		Object execute = interceptor.execute(invoker, service, method, new Object[] {"myId"});
		assertNotNull("result cannot be null.", execute);
		assertEquals("Wrong result type", Long.class, execute.getClass());
		assertEquals("Wrong result", 0L, execute);
	}
