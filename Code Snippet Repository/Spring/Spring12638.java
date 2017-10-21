	@Test
	public void favorParameter() throws Exception {
		this.configurer.favorParameter(true);
		this.configurer.parameterName("f");
		this.configurer.mediaTypes(Collections.singletonMap("json", MediaType.APPLICATION_JSON));
		ContentNegotiationManager manager = this.configurer.buildContentNegotiationManager();

		this.servletRequest.setRequestURI("/flower");
		this.servletRequest.addParameter("f", "json");

		assertEquals(MediaType.APPLICATION_JSON, manager.resolveMediaTypes(this.webRequest).get(0));
	}
