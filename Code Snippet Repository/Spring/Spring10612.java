	@Test
	public void setResultTwice() {
		DeferredResultHandler handler = mock(DeferredResultHandler.class);

		DeferredResult<String> result = new DeferredResult<>();
		result.setResultHandler(handler);

		assertTrue(result.setResult("hello"));
		assertFalse(result.setResult("hi"));

		verify(handler).handleResult("hello");
	}
