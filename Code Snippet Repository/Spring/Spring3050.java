	@Test
	public void testCacheGetCallableFail() {
		Cache cache = this.manager.getCache(createRandomKey());
		String key = createRandomKey();
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
