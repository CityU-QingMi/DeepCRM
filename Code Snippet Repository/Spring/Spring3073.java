	public void testConditionalCacheUpdate(CacheableService<?> service) {
		Integer one = 1;
		Integer three = 3;

		Cache cache = this.cm.getCache("testCache");
		assertEquals(one, Integer.valueOf(service.conditionalUpdate(one).toString()));
		assertNull(cache.get(one));

		assertEquals(three, Integer.valueOf(service.conditionalUpdate(three).toString()));
		assertEquals(three, Integer.valueOf(cache.get(three).get().toString()));
	}
