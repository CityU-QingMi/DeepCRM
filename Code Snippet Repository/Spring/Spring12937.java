	@Test
	public void writeFluxOfString() throws Exception {

		// Default to "text/plain"
		testEmitterContentType("text/plain");

		// Same if no concrete media type
		this.servletRequest.addHeader("Accept", "text/*");
		testEmitterContentType("text/plain");

		// Otherwise pick concrete media type
		this.servletRequest.addHeader("Accept", "*/*, text/*, text/markdown");
		testEmitterContentType("text/markdown");

		// Any concrete media type
		this.servletRequest.addHeader("Accept", "*/*, text/*, foo/bar");
		testEmitterContentType("foo/bar");

		// Including json
		this.servletRequest.addHeader("Accept", "*/*, text/*, application/json");
		testEmitterContentType("application/json");
	}
