	@Test
	public void fullGetConfig() {
		CacheMethodDetails<CacheResult> methodDetails = create(CacheResult.class,
				SampleObject.class, "fullGetConfig", Long.class);
		CacheResultOperation operation = createDefaultOperation(methodDetails);
		assertTrue(operation.isAlwaysInvoked());
		assertNotNull(operation.getExceptionTypeFilter());
		assertTrue(operation.getExceptionTypeFilter().match(IOException.class));
		assertFalse(operation.getExceptionTypeFilter().match(NullPointerException.class));
	}
