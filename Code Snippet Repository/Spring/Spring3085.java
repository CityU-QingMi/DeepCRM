	public void testCacheableSync(CacheableService<?> service) throws Exception {
		Object o1 = new Object();

		Object r1 = service.cacheSync(o1);
		Object r2 = service.cacheSync(o1);
		Object r3 = service.cacheSync(o1);

		assertSame(r1, r2);
		assertSame(r1, r3);
	}
