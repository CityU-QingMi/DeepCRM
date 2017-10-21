	@Test
	public void fromPath() throws URISyntaxException {
		UriComponents result = UriComponentsBuilder.fromPath("foo").queryParam("bar").fragment("baz").build();
		assertEquals("foo", result.getPath());
		assertEquals("bar", result.getQuery());
		assertEquals("baz", result.getFragment());

		assertEquals("Invalid result URI String", "foo?bar#baz", result.toUriString());

		URI expected = new URI("foo?bar#baz");
		assertEquals("Invalid result URI", expected, result.toUri());

		result = UriComponentsBuilder.fromPath("/foo").build();
		assertEquals("/foo", result.getPath());

		expected = new URI("/foo");
		assertEquals("Invalid result URI", expected, result.toUri());
	}
