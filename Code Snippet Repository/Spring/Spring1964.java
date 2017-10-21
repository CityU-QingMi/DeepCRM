	@Test
	public void simplePut() {
		CachePutOperation operation = createSimpleOperation();

		CacheInvocationParameter[] allParameters = operation.getAllParameters(2L, sampleInstance);
		assertEquals(2, allParameters.length);
		assertCacheInvocationParameter(allParameters[0], Long.class, 2L, 0);
		assertCacheInvocationParameter(allParameters[1], SampleObject.class, sampleInstance, 1);

		CacheInvocationParameter valueParameter = operation.getValueParameter(2L, sampleInstance);
		assertNotNull(valueParameter);
		assertCacheInvocationParameter(valueParameter, SampleObject.class, sampleInstance, 1);
	}
