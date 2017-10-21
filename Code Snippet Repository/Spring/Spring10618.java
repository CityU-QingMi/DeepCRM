	@Test
	public void startAsync() throws Exception {
		this.asyncRequest.startAsync();

		MockAsyncContext context = (MockAsyncContext) this.request.getAsyncContext();
		assertNotNull(context);
		assertEquals("Timeout value not set", 44 * 1000, context.getTimeout());
		assertEquals(1, context.getListeners().size());
		assertSame(this.asyncRequest, context.getListeners().get(0));
	}
