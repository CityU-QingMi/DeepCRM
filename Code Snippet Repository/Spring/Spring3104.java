	@Test
	public void evictFailProperException() {
		UnsupportedOperationException exception = new UnsupportedOperationException("Test exception on evict");
		willThrow(exception).given(this.cache).evict(0L);

		this.cacheInterceptor.setErrorHandler(new SimpleCacheErrorHandler());

		this.thrown.expect(is(exception));
		this.simpleService.evict(0L);
	}
