	@Test
	public void getAndPutFail() {
		UnsupportedOperationException exception = new UnsupportedOperationException("Test exception on get");
		willThrow(exception).given(this.cache).get(0L);
		willThrow(exception).given(this.cache).put(0L, 0L); // Update of the cache will fail as well

		Object counter = this.simpleService.get(0L);

		willReturn(new SimpleValueWrapper(2L)).given(this.cache).get(0L);
		Object counter2 = this.simpleService.get(0L);
		Object counter3 = this.simpleService.get(0L);
		assertNotSame(counter, counter2);
		assertEquals(counter2, counter3);
	}
