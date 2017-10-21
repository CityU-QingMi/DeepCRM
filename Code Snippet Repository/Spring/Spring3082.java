	public void testCacheableNull(CacheableService<?> service) throws Exception {
		Object o1 = new Object();
		assertNull(this.cm.getCache("testCache").get(o1));

		Object r1 = service.cacheNull(o1);
		Object r2 = service.cacheNull(o1);
		Object r3 = service.cacheNull(o1);

		assertSame(r1, r2);
		assertSame(r1, r3);

		assertEquals(r3, this.cm.getCache("testCache").get(o1).get());
		assertNull("Cached value should be null", r3);
	}
