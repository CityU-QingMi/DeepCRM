	@Test
	public void testChangeStoreByValue() {
		ConcurrentMapCacheManager cm = new ConcurrentMapCacheManager("c1", "c2");
		assertFalse(cm.isStoreByValue());
		Cache cache1 = cm.getCache("c1");
		assertTrue(cache1 instanceof ConcurrentMapCache);
		assertFalse(((ConcurrentMapCache)cache1).isStoreByValue());
		cache1.put("key", "value");

		cm.setStoreByValue(true);
		assertTrue(cm.isStoreByValue());
		Cache cache1x = cm.getCache("c1");
		assertTrue(cache1x instanceof ConcurrentMapCache);
		assertTrue(cache1x != cache1);
		assertNull(cache1x.get("key"));
	}
