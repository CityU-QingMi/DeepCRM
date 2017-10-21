	@Test
	public void resolveMediaTypesFromMapping() throws Exception {

		this.servletRequest.setRequestURI("test.html");

		PathExtensionContentNegotiationStrategy strategy = new PathExtensionContentNegotiationStrategy();
		List<MediaType> mediaTypes = strategy.resolveMediaTypes(this.webRequest);

		assertEquals(Arrays.asList(new MediaType("text", "html")), mediaTypes);

		Map<String, MediaType> mapping = Collections.singletonMap("HTML", MediaType.APPLICATION_XHTML_XML);
		strategy = new PathExtensionContentNegotiationStrategy(mapping);
		mediaTypes = strategy.resolveMediaTypes(this.webRequest);

		assertEquals(Arrays.asList(new MediaType("application", "xhtml+xml")), mediaTypes);
	}
