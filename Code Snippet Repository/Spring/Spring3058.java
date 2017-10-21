	@SuppressWarnings("")
	@Test
	public void testSerializer() {
		ConcurrentMapCache serializeCache = createCacheWithStoreByValue();
		assertTrue(serializeCache.isStoreByValue());

		Object key = createRandomKey();
		List<String> content = new ArrayList<>();
		content.addAll(Arrays.asList("one", "two", "three"));
		serializeCache.put(key, content);
		content.remove(0);
		List<String> entry = (List<String>) serializeCache.get(key).get();
		assertEquals(3, entry.size());
		assertEquals("one", entry.get(0));
	}
