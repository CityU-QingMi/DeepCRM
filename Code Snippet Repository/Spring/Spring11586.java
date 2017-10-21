	@Test
	public void transformWithGzippedResource() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/static/main.css").build());
		Resource original = new ClassPathResource("test/main.css", getClass());
		createTempCopy("main.css", "main.css.gz");
		GzipResourceResolver.GzippedResource expected = new GzipResourceResolver.GzippedResource(original);
		StepVerifier.create(this.transformerChain.transform(exchange, expected))
				.expectNext(expected)
				.expectComplete().verify();
	}
