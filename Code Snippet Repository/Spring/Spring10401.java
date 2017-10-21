	@Test
	public void getMediaTypeFilenameWithContextPath() throws Exception {

		PathExtensionContentNegotiationStrategy strategy = new PathExtensionContentNegotiationStrategy();

		this.servletRequest.setContextPath("/project-1.0.0.M3");
		this.servletRequest.setRequestURI("/project-1.0.0.M3/");
		assertTrue("Context path should be excluded", strategy.resolveMediaTypes(webRequest).isEmpty());

		this.servletRequest.setRequestURI("/project-1.0.0.M3");
		assertTrue("Context path should be excluded", strategy.resolveMediaTypes(webRequest).isEmpty());
	}
