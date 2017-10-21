	@Test
	public void changeCaffeineRecreateCache() {
		CaffeineCacheManager cm = new CaffeineCacheManager("c1");
		Cache cache1 = cm.getCache("c1");

		Caffeine<Object, Object> caffeine = Caffeine.newBuilder().maximumSize(10);
		cm.setCaffeine(caffeine);
		Cache cache1x = cm.getCache("c1");
		assertTrue(cache1x != cache1);

		cm.setCaffeine(caffeine); // Set same instance
		Cache cache1xx = cm.getCache("c1");
		assertSame(cache1x, cache1xx);
	}
