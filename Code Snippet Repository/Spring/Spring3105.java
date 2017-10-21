	@Test
	public void clearFailProperException() {
		UnsupportedOperationException exception = new UnsupportedOperationException("Test exception on evict");
		willThrow(exception).given(this.cache).clear();

		this.cacheInterceptor.setErrorHandler(new SimpleCacheErrorHandler());

		this.thrown.expect(is(exception));
		this.simpleService.clear();
	}
