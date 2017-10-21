	@Test
	public void simpleGet() {
		CacheResultOperation operation = createSimpleOperation();

		assertNotNull(operation.getKeyGenerator());
		assertNotNull(operation.getExceptionCacheResolver());

		assertNull(operation.getExceptionCacheName());
		assertEquals(defaultExceptionCacheResolver, operation.getExceptionCacheResolver());

		CacheInvocationParameter[] allParameters = operation.getAllParameters(2L);
		assertEquals(1, allParameters.length);
		assertCacheInvocationParameter(allParameters[0], Long.class, 2L, 0);

		CacheInvocationParameter[] keyParameters = operation.getKeyParameters(2L);
		assertEquals(1, keyParameters.length);
		assertCacheInvocationParameter(keyParameters[0], Long.class, 2L, 0);
	}
