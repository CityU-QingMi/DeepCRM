	public void testCacheUpdate(CacheableService<?> service) {
		Object o = new Object();
		Cache cache = this.cm.getCache("testCache");
		assertNull(cache.get(o));
		Object r1 = service.update(o);
		assertSame(r1, cache.get(o).get());

		o = new Object();
		assertNull(cache.get(o));
		Object r2 = service.update(o);
		assertSame(r2, cache.get(o).get());
	}
