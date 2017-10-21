	@Test
	public void from() throws Exception {
		ClientRequest other = ClientRequest.method(GET, URI.create("http://example.com"))
				.header("foo", "bar")
				.cookie("baz", "qux").build();
		ClientRequest result = ClientRequest.from(other)
				.headers(httpHeaders -> httpHeaders.set("foo", "baar"))
				.cookies(cookies -> cookies.set("baz", "quux"))
		.build();
		assertEquals(new URI("http://example.com"), result.url());
		assertEquals(GET, result.method());
		assertEquals(1, result.headers().size());
		assertEquals("baar", result.headers().getFirst("foo"));
		assertEquals(1, result.cookies().size());
		assertEquals("quux", result.cookies().getFirst("baz"));
	}
