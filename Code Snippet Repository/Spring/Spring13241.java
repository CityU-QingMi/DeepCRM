	@Test
	public void transformExtLinksNotAllowed() throws Exception {
		this.request = new MockHttpServletRequest("GET", "/static/external.css");
		ResourceResolverChain resolverChain = Mockito.mock(DefaultResourceResolverChain.class);
		ResourceTransformerChain transformerChain = new DefaultResourceTransformerChain(resolverChain,
				Arrays.asList(new CssLinkResourceTransformer()));

		Resource externalCss = new ClassPathResource("test/external.css", getClass());
		Resource resource = transformerChain.transform(this.request, externalCss);
		TransformedResource transformedResource = (TransformedResource) resource;

		String expected = "@import url(\"http://example.org/fonts/css\");\n" +
				"body { background: url(\"file:///home/spring/image.png\") }\n" +
				"figure { background: url(\"//example.org/style.css\")}";
		String result = new String(transformedResource.getByteArray(), StandardCharsets.UTF_8);
		result = StringUtils.deleteAny(result, "\r");
		assertEquals(expected, result);

		Mockito.verify(resolverChain, Mockito.never())
				.resolveUrlPath("http://example.org/fonts/css", Arrays.asList(externalCss));
		Mockito.verify(resolverChain, Mockito.never())
				.resolveUrlPath("file:///home/spring/image.png", Arrays.asList(externalCss));
		Mockito.verify(resolverChain, Mockito.never())
				.resolveUrlPath("//example.org/style.css", Arrays.asList(externalCss));
	}
