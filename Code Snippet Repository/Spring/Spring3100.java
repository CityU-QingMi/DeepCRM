	@Test
	public void getFail() {
		UnsupportedOperationException exception = new UnsupportedOperationException("Test exception on get");
		willThrow(exception).given(this.cache).get(0L);

		Object result = this.simpleService.get(0L);
		verify(this.errorHandler).handleCacheGetError(exception, cache, 0L);
		verify(this.cache).get(0L);
		verify(this.cache).put(0L, result); // result of the invocation
	}
