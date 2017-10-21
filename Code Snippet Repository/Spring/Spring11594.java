	@Test
	public void resolveUrlPath() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("/resources/main.css").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		String resourcePath = "/resources/bar.css";
		Resource css = new ClassPathResource("test/main.css", getClass());
		String actual = this.transformer.resolveUrlPath(
				resourcePath, exchange, css, this.transformerChain).block(Duration.ofSeconds(5));

		assertEquals("/resources/bar-11e16cf79faee7ac698c805cf28248d2.css", actual);
		assertEquals("/resources/bar-11e16cf79faee7ac698c805cf28248d2.css", actual);
	}
