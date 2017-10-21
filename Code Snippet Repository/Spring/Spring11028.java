	@Test
	public void port() {
		UriComponents uri1 = fromUriString("http://example.com:8080/bar").build();
		UriComponents uri2 = fromUriString("http://example.com/bar").port(8080).build();
		UriComponents uri3 = fromUriString("http://example.com/bar").port("{port}").build().expand(8080);
		UriComponents uri4 = fromUriString("http://example.com/bar").port("808{digit}").build().expand(0);
		assertEquals(8080, uri1.getPort());
		assertEquals("http://example.com:8080/bar", uri1.toUriString());
		assertEquals(8080, uri2.getPort());
		assertEquals("http://example.com:8080/bar", uri2.toUriString());
		assertEquals(8080, uri3.getPort());
		assertEquals("http://example.com:8080/bar", uri3.toUriString());
		assertEquals(8080, uri4.getPort());
		assertEquals("http://example.com:8080/bar", uri4.toUriString());
	}
