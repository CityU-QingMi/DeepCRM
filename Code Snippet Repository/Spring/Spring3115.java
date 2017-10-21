	@Test
	public void testMultipleCachingSource() throws Exception {
		Collection<CacheOperation> ops = getOps("multipleCaching");
		assertEquals(2, ops.size());
		Iterator<CacheOperation> it = ops.iterator();
		CacheOperation next = it.next();
		assertTrue(next instanceof CacheableOperation);
		assertTrue(next.getCacheNames().contains("test"));
		assertEquals("#a", next.getKey());
		next = it.next();
		assertTrue(next instanceof CacheableOperation);
		assertTrue(next.getCacheNames().contains("test"));
		assertEquals("#b", next.getKey());
	}
