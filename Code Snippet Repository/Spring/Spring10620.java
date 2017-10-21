	@Test
	public void startAsyncNotSupported() throws Exception {
		this.request.setAsyncSupported(false);
		try {
			this.asyncRequest.startAsync();
			fail("expected exception");
		}
		catch (IllegalStateException ex) {
			assertThat(ex.getMessage(), containsString("Async support must be enabled"));
		}
	}
