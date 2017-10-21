	@Test
	public void replacePath() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://www.ietf.org/rfc/rfc2396.txt");
		builder.replacePath("/rfc/rfc3986.txt");
		UriComponents result = builder.build();

		assertEquals("http://www.ietf.org/rfc/rfc3986.txt", result.toUriString());

		builder = UriComponentsBuilder.fromUriString("http://www.ietf.org/rfc/rfc2396.txt");
		builder.replacePath(null);
		result = builder.build();

		assertEquals("http://www.ietf.org", result.toUriString());
	}
