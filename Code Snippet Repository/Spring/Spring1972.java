	@Test
	public void getFailPutExceptionFail() {
		UnsupportedOperationException exceptionOnPut = new UnsupportedOperationException("Test exception on put");
		Object key = SimpleKeyGenerator.generateKey(0L);
		given(this.cache.get(key)).willReturn(null);
		willThrow(exceptionOnPut).given(this.errorCache).put(key, SimpleService.TEST_EXCEPTION);

		try {
			this.simpleService.getFail(0L);
		}
		catch (IllegalStateException ex) {
			assertEquals("Test exception", ex.getMessage());
		}
		verify(this.errorHandler).handleCachePutError(
				exceptionOnPut, this.errorCache, key, SimpleService.TEST_EXCEPTION);
	}
