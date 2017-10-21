	@Test
	public void transform() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("/static/main.css").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		Resource css = new ClassPathResource("test/main.css", getClass());

		String expected = "\n" +
				"@import url(\"/static/bar-11e16cf79faee7ac698c805cf28248d2.css?#iefix\");\n" +
				"@import url('/static/bar-11e16cf79faee7ac698c805cf28248d2.css#bla-normal');\n" +
				"@import url(/static/bar-11e16cf79faee7ac698c805cf28248d2.css);\n\n" +
				"@import \"/static/foo-e36d2e05253c6c7085a91522ce43a0b4.css\";\n" +
				"@import '/static/foo-e36d2e05253c6c7085a91522ce43a0b4.css';\n\n" +
				"body { background: url(\"/static/images/image-f448cd1d5dba82b774f3202c878230b3.png?#iefix\") }\n";

		StepVerifier.create(this.transformerChain.transform(exchange, css).cast(TransformedResource.class))
				.consumeNextWith(resource -> {
					String result = new String(resource.getByteArray(), StandardCharsets.UTF_8);
					result = StringUtils.deleteAny(result, "\r");
					assertEquals(expected, result);
				})
				.expectComplete().verify();
	}
