	@Test
	public void putFailProperException() {
		UnsupportedOperationException exception = new UnsupportedOperationException("Test exception on put");
		willThrow(exception).given(this.cache).put(0L, 0L);

		this.cacheInterceptor.setErrorHandler(new SimpleCacheErrorHandler());

		this.thrown.expect(is(exception));
		this.simpleService.put(0L);
	}
