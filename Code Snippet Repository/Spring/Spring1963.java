	private void assertJCacheResolver(CacheResolver actual,
			Class<? extends javax.cache.annotation.CacheResolver> expectedTargetType) {

		if (expectedTargetType == null) {
			assertNull(actual);
		}
		else {
			assertEquals("Wrong cache resolver implementation", CacheResolverAdapter.class, actual.getClass());
			CacheResolverAdapter adapter = (CacheResolverAdapter) actual;
			assertEquals("Wrong target JCache implementation", expectedTargetType, adapter.getTarget().getClass());
		}
	}
