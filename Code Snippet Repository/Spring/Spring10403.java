	@Test
	public void resolveMediaTypesIgnoreUnknownExtension() throws Exception {

		this.servletRequest.setRequestURI("test.foobar");

		PathExtensionContentNegotiationStrategy strategy = new PathExtensionContentNegotiationStrategy();
		List<MediaType> mediaTypes = strategy.resolveMediaTypes(this.webRequest);

		assertEquals(Collections.<MediaType>emptyList(), mediaTypes);
	}
