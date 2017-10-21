	@Test
	public void resolveGzippedFile() throws IOException {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("")
				.header("Accept-Encoding", "gzip").build());

		String file = "js/foo.js";
		Resource resolved = this.resolver.resolveResource(exchange, file, this.locations).block(TIMEOUT);

		String gzFile = file+".gz";
		Resource resource = new ClassPathResource("test/" + gzFile, getClass());
		assertEquals(resource.getDescription(), resolved.getDescription());
		assertEquals(new ClassPathResource("test/" + file).getFilename(), resolved.getFilename());
		assertTrue("Expected " + resolved + " to be of type " + HttpResource.class,
				resolved instanceof HttpResource);
	}
