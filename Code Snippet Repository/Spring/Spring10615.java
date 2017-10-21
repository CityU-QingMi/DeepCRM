	@Test
	public void onCompletion() throws Exception {
		final StringBuilder sb = new StringBuilder();

		DeferredResult<String> result = new DeferredResult<>();
		result.onCompletion(new Runnable() {
			@Override
			public void run() {
				sb.append("completion event");
			}
		});

		result.getInterceptor().afterCompletion(null, null);

		assertTrue(result.isSetOrExpired());
		assertEquals("completion event", sb.toString());
	}
