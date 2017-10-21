	@Test
	public void fromUriString() {
		UriComponents result = UriComponentsBuilder.fromUriString("http://www.ietf.org/rfc/rfc3986.txt").build();
		assertEquals("http", result.getScheme());
		assertNull(result.getUserInfo());
		assertEquals("www.ietf.org", result.getHost());
		assertEquals(-1, result.getPort());
		assertEquals("/rfc/rfc3986.txt", result.getPath());
		assertEquals(Arrays.asList("rfc", "rfc3986.txt"), result.getPathSegments());
		assertNull(result.getQuery());
		assertNull(result.getFragment());

		result = UriComponentsBuilder.fromUriString(
				"http://arjen:foobar@java.sun.com:80/javase/6/docs/api/java/util/BitSet.html?foo=bar#and(java.util.BitSet)")
				.build();
		assertEquals("http", result.getScheme());
		assertEquals("arjen:foobar", result.getUserInfo());
		assertEquals("java.sun.com", result.getHost());
		assertEquals(80, result.getPort());
		assertEquals("/javase/6/docs/api/java/util/BitSet.html", result.getPath());
		assertEquals("foo=bar", result.getQuery());
		MultiValueMap<String, String> expectedQueryParams = new LinkedMultiValueMap<>(1);
		expectedQueryParams.add("foo", "bar");
		assertEquals(expectedQueryParams, result.getQueryParams());
		assertEquals("and(java.util.BitSet)", result.getFragment());

		result = UriComponentsBuilder.fromUriString("mailto:java-net@java.sun.com#baz").build();
		assertEquals("mailto", result.getScheme());
		assertNull(result.getUserInfo());
		assertNull(result.getHost());
		assertEquals(-1, result.getPort());
		assertEquals("java-net@java.sun.com", result.getSchemeSpecificPart());
		assertNull(result.getPath());
		assertNull(result.getQuery());
		assertEquals("baz", result.getFragment());

		result = UriComponentsBuilder.fromUriString("docs/guide/collections/designfaq.html#28").build();
		assertNull(result.getScheme());
		assertNull(result.getUserInfo());
		assertNull(result.getHost());
		assertEquals(-1, result.getPort());
		assertEquals("docs/guide/collections/designfaq.html", result.getPath());
		assertNull(result.getQuery());
		assertEquals("28", result.getFragment());
	}
