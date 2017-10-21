	@Test
	public void getPutNewElementFail() {
		UnsupportedOperationException exception = new UnsupportedOperationException("Test exception on put");
		Object key = SimpleKeyGenerator.generateKey(0L);
		given(this.cache.get(key)).willReturn(null);
		willThrow(exception).given(this.cache).put(key, 0L);

		this.simpleService.get(0L);
		verify(this.errorHandler).handleCachePutError(exception, this.cache, key, 0L);
	}
