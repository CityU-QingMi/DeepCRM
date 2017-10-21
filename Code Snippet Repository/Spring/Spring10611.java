	@Test
	public void setResult() {
		DeferredResultHandler handler = mock(DeferredResultHandler.class);

		DeferredResult<String> result = new DeferredResult<>();
		result.setResultHandler(handler);

		assertTrue(result.setResult("hello"));
		verify(handler).handleResult("hello");
	}
