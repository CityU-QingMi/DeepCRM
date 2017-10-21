	@Test
	public void isSetOrExpired() {
		DeferredResultHandler handler = mock(DeferredResultHandler.class);

		DeferredResult<String> result = new DeferredResult<>();
		result.setResultHandler(handler);

		assertFalse(result.isSetOrExpired());

		result.setResult("hello");

		assertTrue(result.isSetOrExpired());

		verify(handler).handleResult("hello");
	}
