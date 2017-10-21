	@Test
	public void getMediaTypeFilenameWithEncodedURI() throws Exception {

		this.servletRequest.setRequestURI("/quo%20vadis%3f.html");

		PathExtensionContentNegotiationStrategy strategy = new PathExtensionContentNegotiationStrategy();
		List<MediaType> result = strategy.resolveMediaTypes(webRequest);

		assertEquals("Invalid content type", Collections.singletonList(new MediaType("text", "html")), result);
	}
