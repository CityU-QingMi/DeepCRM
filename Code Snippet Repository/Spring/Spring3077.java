	public void testMultiCacheAndEvict(CacheableService<?> service) {
		String methodName = "multiCacheAndEvict";

		Cache primary = this.cm.getCache("primary");
		Cache secondary = this.cm.getCache("secondary");
		Object key = 1;

		secondary.put(key, key);

		assertNull(secondary.get(methodName));
		assertSame(key, secondary.get(key).get());

		Object r1 = service.multiCacheAndEvict(key);
		assertSame(r1, service.multiCacheAndEvict(key));

		// assert the method name is used
		assertSame(r1, primary.get(methodName).get());
		assertNull(secondary.get(methodName));
		assertNull(secondary.get(key));
	}
