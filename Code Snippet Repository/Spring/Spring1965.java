	@Test
	public void fullPutConfig() {
		CacheMethodDetails<CachePut> methodDetails = create(CachePut.class,
				SampleObject.class, "fullPutConfig", Long.class, SampleObject.class);
		CachePutOperation operation = createDefaultOperation(methodDetails);
		assertTrue(operation.isEarlyPut());
		assertNotNull(operation.getExceptionTypeFilter());
		assertTrue(operation.getExceptionTypeFilter().match(IOException.class));
		assertFalse(operation.getExceptionTypeFilter().match(NullPointerException.class));
	}
