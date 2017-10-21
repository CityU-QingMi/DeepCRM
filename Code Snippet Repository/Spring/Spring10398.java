	@Test
	public void resolveMediaTypesHandleNoMatch() throws Exception {
		Map<String, MediaType> mapping = null;
		TestMappingContentNegotiationStrategy strategy = new TestMappingContentNegotiationStrategy("xml", mapping);

		List<MediaType> mediaTypes = strategy.resolveMediaTypes(null);

		assertEquals(1, mediaTypes.size());
		assertEquals("application/xml", mediaTypes.get(0).toString());
	}
