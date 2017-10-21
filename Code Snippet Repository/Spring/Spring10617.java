	@Test
	public void onError() throws Exception {
		final StringBuilder sb = new StringBuilder();

		DeferredResultHandler handler = mock(DeferredResultHandler.class);

		DeferredResult<String> result = new DeferredResult<>(null, "error result");
		result.setResultHandler(handler);
		Exception e = new Exception();
		result.onError(new Consumer<Throwable>() {
			@Override
			public void accept(Throwable t) {
				sb.append("error event");
			}
		});

		result.getInterceptor().handleError(null, null, e);

		assertEquals("error event", sb.toString());
		assertFalse("Should not be able to set result a second time", result.setResult("hello"));
		verify(handler).handleResult(e);
	}
