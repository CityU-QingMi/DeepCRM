	@Test
	public void testCacheGetCallableFail() {
		T cache = getCache();

		String key = createRandomKey();
		assertNull(cache.get(key));

		try {
			cache.get(key, () -> {
				throw new UnsupportedOperationException("Expected exception");
			});
		}
		catch (Cache.ValueRetrievalException ex) {
			assertNotNull(ex.getCause());
			assertEquals(UnsupportedOperationException.class, ex.getCause().getClass());
		}
	}
