	@Test
	public void resolveFromCacheWithEncodingVariants() throws IOException {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("")
				.header("Accept-Encoding", "gzip").build());

		String file = "js/foo.js";
		Resource resolved = this.resolver.resolveResource(exchange, file, this.locations).block(TIMEOUT);

		String gzFile = file+".gz";
		Resource gzResource = new ClassPathResource("test/"+gzFile, getClass());
		assertEquals(gzResource.getDescription(), resolved.getDescription());
		assertEquals(new ClassPathResource("test/" + file).getFilename(), resolved.getFilename());
		assertTrue("Expected " + resolved + " to be of type " + HttpResource.class,
				resolved instanceof HttpResource);

		// resolved resource is now cached in CachingResourceResolver

		exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/js/foo.js").build());
		resolved = this.resolver.resolveResource(exchange, file, this.locations).block(TIMEOUT);

		Resource resource = new ClassPathResource("test/"+file, getClass());
		assertEquals(resource.getDescription(), resolved.getDescription());
		assertEquals(new ClassPathResource("test/" + file).getFilename(), resolved.getFilename());
		assertFalse("Expected " + resolved + " to *not* be of type " + HttpResource.class,
				resolved instanceof HttpResource);
	}
