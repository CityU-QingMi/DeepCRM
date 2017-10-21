	@Test
	public void fromHierarchicalUri() throws URISyntaxException {
		URI uri = new URI("http://example.com/foo?bar#baz");
		UriComponents result = UriComponentsBuilder.fromUri(uri).build();
		assertEquals("http", result.getScheme());
		assertEquals("example.com", result.getHost());
		assertEquals("/foo", result.getPath());
		assertEquals("bar", result.getQuery());
		assertEquals("baz", result.getFragment());

		assertEquals("Invalid result URI", uri, result.toUri());
	}
