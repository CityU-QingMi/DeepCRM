	@Test
	public void transformExtLinksNotAllowed() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/static/external.css").build());
		ResourceResolverChain resolverChain = Mockito.mock(DefaultResourceResolverChain.class);
		ResourceTransformerChain transformerChain = new DefaultResourceTransformerChain(resolverChain,
				Collections.singletonList(new CssLinkResourceTransformer()));

		Resource externalCss = new ClassPathResource("test/external.css", getClass());
		StepVerifier.create(transformerChain.transform(exchange, externalCss).cast(TransformedResource.class))
				.consumeNextWith(resource -> {
					String expected = "@import url(\"http://example.org/fonts/css\");\n" +
							"body { background: url(\"file:///home/spring/image.png\") }\n" +
							"figure { background: url(\"//example.org/style.css\")}";
					String result = new String(resource.getByteArray(), StandardCharsets.UTF_8);
					result = StringUtils.deleteAny(result, "\r");
					assertEquals(expected, result);
				}).expectComplete().verify();

		Mockito.verify(resolverChain, Mockito.never())
				.resolveUrlPath("http://example.org/fonts/css", Collections.singletonList(externalCss));
		Mockito.verify(resolverChain, Mockito.never())
				.resolveUrlPath("file:///home/spring/image.png", Collections.singletonList(externalCss));
		Mockito.verify(resolverChain, Mockito.never())
				.resolveUrlPath("//example.org/style.css", Collections.singletonList(externalCss));
	}
