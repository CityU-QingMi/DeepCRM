	@Test
	public void transform() throws Exception {
		this.request = new MockHttpServletRequest("GET", "/static/main.css");
		Resource css = new ClassPathResource("test/main.css", getClass());
		TransformedResource actual = (TransformedResource) this.transformerChain.transform(this.request, css);

		String expected = "\n" +
				"@import url(\"/static/bar-11e16cf79faee7ac698c805cf28248d2.css?#iefix\");\n" +
				"@import url('/static/bar-11e16cf79faee7ac698c805cf28248d2.css#bla-normal');\n" +
				"@import url(/static/bar-11e16cf79faee7ac698c805cf28248d2.css);\n\n" +
				"@import \"/static/foo-e36d2e05253c6c7085a91522ce43a0b4.css\";\n" +
				"@import '/static/foo-e36d2e05253c6c7085a91522ce43a0b4.css';\n\n" +
				"body { background: url(\"/static/images/image-f448cd1d5dba82b774f3202c878230b3.png?#iefix\") }\n";

		String result = new String(actual.getByteArray(), StandardCharsets.UTF_8);
		result = StringUtils.deleteAny(result, "\r");
		assertEquals(expected, result);
	}
