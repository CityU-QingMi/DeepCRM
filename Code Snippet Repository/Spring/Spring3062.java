	public void testEvictAll(CacheableService<?> service) throws Exception {
		Object o1 = new Object();

		Object r1 = service.cache(o1);
		Object r2 = service.cache(o1);

		Object o2 = new Object();
		Object r10 = service.cache(o2);

		assertSame(r1, r2);
		assertNotSame(r1, r10);
		service.evictAll(new Object());
		Cache cache = this.cm.getCache("testCache");
		assertNull(cache.get(o1));
		assertNull(cache.get(o2));

		Object r3 = service.cache(o1);
		Object r4 = service.cache(o1);
		assertNotSame(r1, r3);
		assertSame(r3, r4);
	}
