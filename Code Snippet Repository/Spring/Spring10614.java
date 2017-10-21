	@Test
	public void hasResult() {
		DeferredResultHandler handler = mock(DeferredResultHandler.class);

		DeferredResult<String> result = new DeferredResult<>();
		result.setResultHandler(handler);

		assertFalse(result.hasResult());
		assertNull(result.getResult());

		result.setResult("hello");

		assertEquals("hello", result.getResult());
	}
