	@Test
	public void getAndPut() {
		this.cache.clear();

		long key = 1;
		Long value = this.service.getAndPut(key);

		assertEquals("Wrong value for @Cacheable key", value, this.cache.get(key).get());
		assertEquals("Wrong value for @CachePut key", value, this.cache.get(value + 100).get()); // See @CachePut

		// CachePut forced a method call
		Long anotherValue = this.service.getAndPut(key);
		assertNotSame(value, anotherValue);
		// NOTE: while you might expect the main key to have been updated, it hasn't. @Cacheable operations
		// are only processed in case of a cache miss. This is why combining @Cacheable with @CachePut
		// is a very bad idea. We could refine the condition now that we can figure out if we are going
		// to invoke the method anyway but that brings a whole new set of potential regressions.
		//assertEquals("Wrong value for @Cacheable key", anotherValue, cache.get(key).get());
		assertEquals("Wrong value for @CachePut key", anotherValue, this.cache.get(anotherValue + 100).get());
	}
