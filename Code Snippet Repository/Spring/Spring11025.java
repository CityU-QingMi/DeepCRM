	@Test
	public void fromUriStringIPv6Host() throws URISyntaxException {
		UriComponents result = UriComponentsBuilder
				.fromUriString("http://[1abc:2abc:3abc::5ABC:6abc]:8080/resource").build().encode();
		assertEquals("[1abc:2abc:3abc::5ABC:6abc]", result.getHost());

		UriComponents resultWithScopeId = UriComponentsBuilder
				.fromUriString("http://[1abc:2abc:3abc::5ABC:6abc%eth0]:8080/resource").build().encode();
		assertEquals("[1abc:2abc:3abc::5ABC:6abc%25eth0]", resultWithScopeId.getHost());

		UriComponents resultIPv4compatible = UriComponentsBuilder
				.fromUriString("http://[::192.168.1.1]:8080/resource").build().encode();
		assertEquals("[::192.168.1.1]", resultIPv4compatible.getHost());
	}
