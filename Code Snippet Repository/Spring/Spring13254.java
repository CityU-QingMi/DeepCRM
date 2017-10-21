	@Test
	public void getResourceWithMediaTypeResolvedThroughServletContext() throws Exception {
		MockServletContext servletContext = new MockServletContext() {

			@Override
			public String getMimeType(String filePath) {
				return "foo/bar";
			}

			@Override
			public String getVirtualServerName() {
				return null;
			}
		};

		List<Resource> paths = Collections.singletonList(new ClassPathResource("test/", getClass()));
		ResourceHttpRequestHandler handler = new ResourceHttpRequestHandler();
		handler.setServletContext(servletContext);
		handler.setLocations(paths);
		handler.afterPropertiesSet();

		this.request.setAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "foo.css");
		handler.handleRequest(this.request, this.response);

		assertEquals("foo/bar", this.response.getContentType());
		assertEquals("h1 { color:red; }", this.response.getContentAsString());
	}
