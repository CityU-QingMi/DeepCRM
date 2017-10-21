	@Test
	public void fromOpaqueUri() throws URISyntaxException {
		URI uri = new URI("mailto:foo@bar.com#baz");
		UriComponents result = UriComponentsBuilder.fromUri(uri).build();
		assertEquals("mailto", result.getScheme());
		assertEquals("foo@bar.com", result.getSchemeSpecificPart());
		assertEquals("baz", result.getFragment());

		assertEquals("Invalid result URI", uri, result.toUri());
	}
