	@Test
	public void onTimeout() throws Exception {
		final StringBuilder sb = new StringBuilder();

		DeferredResultHandler handler = mock(DeferredResultHandler.class);

		DeferredResult<String> result = new DeferredResult<>(null, "timeout result");
		result.setResultHandler(handler);
		result.onTimeout(new Runnable() {
			@Override
			public void run() {
				sb.append("timeout event");
			}
		});

		result.getInterceptor().handleTimeout(null, null);

		assertEquals("timeout event", sb.toString());
		assertFalse("Should not be able to set result a second time", result.setResult("hello"));
		verify(handler).handleResult("timeout result");
	}
