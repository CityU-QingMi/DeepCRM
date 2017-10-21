	@Test
	public void startAsyncAfterCompleted() throws Exception {
		this.asyncRequest.onComplete(new AsyncEvent(new MockAsyncContext(this.request, this.response)));
		try {
			this.asyncRequest.startAsync();
			fail("expected exception");
		}
		catch (IllegalStateException ex) {
			assertEquals("Async processing has already completed", ex.getMessage());
		}
	}
