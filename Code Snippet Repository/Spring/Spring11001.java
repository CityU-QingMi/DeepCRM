	@Test
	public void multipleFromSameBuilder() throws URISyntaxException {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance().scheme("http").host("example.com").pathSegment("foo");
		UriComponents result1 = builder.build();
		builder = builder.pathSegment("foo2").queryParam("bar").fragment("baz");
		UriComponents result2 = builder.build();

		assertEquals("http", result1.getScheme());
		assertEquals("example.com", result1.getHost());
		assertEquals("/foo", result1.getPath());
		URI expected = new URI("http://example.com/foo");
		assertEquals("Invalid result URI", expected, result1.toUri());

		assertEquals("http", result2.getScheme());
		assertEquals("example.com", result2.getHost());
		assertEquals("/foo/foo2", result2.getPath());
		assertEquals("bar", result2.getQuery());
		assertEquals("baz", result2.getFragment());
		expected = new URI("http://example.com/foo/foo2?bar#baz");
		assertEquals("Invalid result URI", expected, result2.toUri());
	}
