	@Test
	public void testInvalidSerializedContent() {
		ConcurrentMapCache serializeCache = createCacheWithStoreByValue();

		String key = createRandomKey();
		this.nativeCache.put(key, "Some garbage");
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Failed to deserialize");
		this.thrown.expectMessage("Some garbage");
		serializeCache.get(key);
	}
