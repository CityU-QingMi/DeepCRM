	@Test
	public void multiParameterKey() {
		CacheMethodDetails<CacheResult> methodDetails = create(CacheResult.class,
				SampleObject.class, "multiKeysGet", Long.class, Boolean.class, String.class);
		CacheResultOperation operation = createDefaultOperation(methodDetails);

		CacheInvocationParameter[] keyParameters = operation.getKeyParameters(3L, Boolean.TRUE, "Foo");
		assertEquals(2, keyParameters.length);
		assertCacheInvocationParameter(keyParameters[0], Long.class, 3L, 0);
		assertCacheInvocationParameter(keyParameters[1], String.class, "Foo", 2);
	}
