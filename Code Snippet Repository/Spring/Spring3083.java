	@Test
	public void testCustomCacheManager() {
		CacheManager customCm = this.ctx.getBean("customCacheManager", CacheManager.class);
		Object key = new Object();
		Object r1 = this.cs.customCacheManager(key);
		assertSame(r1, this.cs.customCacheManager(key));

		Cache cache = customCm.getCache("testCache");
		assertNotNull(cache.get(key));
	}
