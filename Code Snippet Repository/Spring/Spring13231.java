	@Test
	public void noTransformIfExtensionNoMatch() throws Exception {
		this.chain = mock(ResourceTransformerChain.class);
		this.request = mock(HttpServletRequest.class);
		Resource resource = mock(Resource.class);
		given(resource.getFilename()).willReturn("foobar.file");
		given(this.chain.transform(this.request, resource)).willReturn(resource);

		Resource result = this.transformer.transform(this.request, resource, this.chain);
		assertEquals(resource, result);
	}
