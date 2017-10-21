	@Test
	public void testCachePutNullValueAllowNullFalse() {
		T cache = getCache(false);
		String key = createRandomKey();

		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage(CACHE_NAME_NO_NULL);
		this.thrown.expectMessage(
				"is configured to not allow null values but null was provided");
		cache.put(key, null);
	}
