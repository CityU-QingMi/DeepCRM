	public void testEvictException(CacheableService<?> service) throws Exception {
		Object o1 = new Object();

		Object r1 = service.cache(o1);
		Object r2 = service.cache(o1);

		assertSame(r1, r2);
		try {
			service.evictWithException(o1);
		}
		catch (RuntimeException ex) {
			// expected
		}
		// exception occurred, eviction skipped, data should still be in the cache
		Object r3 = service.cache(o1);
		assertSame(r1, r3);
	}
