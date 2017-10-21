	@Test
	public void defaultSettings() throws Exception {
		ContentNegotiationManager manager = this.configurer.buildContentNegotiationManager();

		this.servletRequest.setRequestURI("/flower.gif");

		assertEquals("Should be able to resolve file extensions by default",
				MediaType.IMAGE_GIF, manager.resolveMediaTypes(this.webRequest).get(0));

		this.servletRequest.setRequestURI("/flower?format=gif");
		this.servletRequest.addParameter("format", "gif");

		assertEquals("Should not resolve request parameters by default",
				Collections.emptyList(), manager.resolveMediaTypes(this.webRequest));

		this.servletRequest.setRequestURI("/flower");
		this.servletRequest.addHeader("Accept", MediaType.IMAGE_GIF_VALUE);

		assertEquals("Should resolve Accept header by default",
				MediaType.IMAGE_GIF, manager.resolveMediaTypes(this.webRequest).get(0));
	}
