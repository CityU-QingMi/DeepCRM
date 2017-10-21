	@Test
	public void replaceQuery() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://example.com/foo?foo=bar&baz=qux");
		builder.replaceQuery("baz=42");
		UriComponents result = builder.build();

		assertEquals("http://example.com/foo?baz=42", result.toUriString());

		builder = UriComponentsBuilder.fromUriString("http://example.com/foo?foo=bar&baz=qux");
		builder.replaceQuery(null);
		result = builder.build();

		assertEquals("http://example.com/foo", result.toUriString());
	}
