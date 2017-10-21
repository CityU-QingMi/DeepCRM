	@Test
	public void ignoreAcceptHeader() throws Exception {
		this.configurer.ignoreAcceptHeader(true);
		ContentNegotiationManager manager = this.configurer.buildContentNegotiationManager();

		this.servletRequest.setRequestURI("/flower");
		this.servletRequest.addHeader("Accept", MediaType.IMAGE_GIF_VALUE);

		assertEquals(Collections.emptyList(), manager.resolveMediaTypes(this.webRequest));
	}
