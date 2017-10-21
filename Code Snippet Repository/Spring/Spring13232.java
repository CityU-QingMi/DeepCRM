	@Test
	public void syntaxErrorInManifest() throws Exception {
		this.chain = mock(ResourceTransformerChain.class);
		this.request = mock(HttpServletRequest.class);
		Resource resource = new ClassPathResource("test/error.appcache", getClass());
		given(this.chain.transform(this.request, resource)).willReturn(resource);

		Resource result = this.transformer.transform(this.request, resource, this.chain);
		assertEquals(resource, result);
	}
