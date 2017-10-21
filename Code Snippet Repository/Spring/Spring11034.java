	@Test
	public void decode() throws UnsupportedEncodingException {
		assertEquals("Invalid encoded URI", "", UriUtils.decode("", CHARSET));
		assertEquals("Invalid encoded URI", "foobar", UriUtils.decode("foobar", CHARSET));
		assertEquals("Invalid encoded URI", "foo bar", UriUtils.decode("foo%20bar", CHARSET));
		assertEquals("Invalid encoded URI", "foo+bar", UriUtils.decode("foo%2bbar", CHARSET));
		assertEquals("Invalid encoded result", "T\u014dky\u014d", UriUtils.decode("T%C5%8Dky%C5%8D", CHARSET));
		assertEquals("Invalid encoded result", "/Z\u00fcrich", UriUtils.decode("/Z%C3%BCrich", CHARSET));
		assertEquals("Invalid encoded result", "T\u014dky\u014d", UriUtils.decode("T\u014dky\u014d", CHARSET));
	}
