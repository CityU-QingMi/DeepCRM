	@Test
	public void getResourceWithRegisteredMediaType() throws Exception {
		ContentNegotiationManagerFactoryBean factory = new ContentNegotiationManagerFactoryBean();
		factory.addMediaType("bar", new MediaType("foo", "bar"));
		factory.afterPropertiesSet();
		ContentNegotiationManager manager = factory.getObject();

		List<Resource> paths = Collections.singletonList(new ClassPathResource("test/", getClass()));
		ResourceHttpRequestHandler handler = new ResourceHttpRequestHandler();
		handler.setServletContext(new MockServletContext());
		handler.setLocations(paths);
		handler.setContentNegotiationManager(manager);
		handler.afterPropertiesSet();

		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "foo.bar");
		handler.handleRequest(this.request, this.response);

		assertEquals("foo/bar", this.response.getContentType());
		assertEquals("h1 { color:red; }", this.response.getContentAsString());
	}
