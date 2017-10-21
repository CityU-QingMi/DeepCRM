	@Test
	public void getFailProperException() {
		UnsupportedOperationException exception = new UnsupportedOperationException("Test exception on get");
		willThrow(exception).given(this.cache).get(0L);

		this.cacheInterceptor.setErrorHandler(new SimpleCacheErrorHandler());

		this.thrown.expect(is(exception));
		this.simpleService.get(0L);
	}
