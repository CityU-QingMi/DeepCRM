	public void testMultiConditionalCacheAndEvict(CacheableService<?> service) {
		Cache primary = this.cm.getCache("primary");
		Cache secondary = this.cm.getCache("secondary");
		Object key = 1;

		secondary.put(key, key);

		assertNull(primary.get(key));
		assertSame(key, secondary.get(key).get());

		Object r1 = service.multiConditionalCacheAndEvict(key);
		Object r3 = service.multiConditionalCacheAndEvict(key);

		assertTrue(!r1.equals(r3));
		assertNull(primary.get(key));

		Object key2 = 3;
		Object r2 = service.multiConditionalCacheAndEvict(key2);
		assertSame(r2, service.multiConditionalCacheAndEvict(key2));

		// assert the method name is used
		assertSame(r2, primary.get(key2).get());
		assertNull(secondary.get(key2));
	}
