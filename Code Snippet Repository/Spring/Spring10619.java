	@Test
	public void startAsyncMultipleTimes() throws Exception {
		this.asyncRequest.startAsync();
		this.asyncRequest.startAsync();
		this.asyncRequest.startAsync();
		this.asyncRequest.startAsync();	// idempotent

		MockAsyncContext context = (MockAsyncContext) this.request.getAsyncContext();
		assertNotNull(context);
		assertEquals(1, context.getListeners().size());
	}
