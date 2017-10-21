	@Test
	public void testClone() throws URISyntaxException {
		UriComponentsBuilder builder1 = UriComponentsBuilder.newInstance();
		builder1.scheme("http").host("e1.com").path("/p1").pathSegment("ps1").queryParam("q1").fragment("f1");

		UriComponentsBuilder builder2 = (UriComponentsBuilder) builder1.clone();
		builder2.scheme("https").host("e2.com").path("p2").pathSegment("ps2").queryParam("q2").fragment("f2");

		UriComponents result1 = builder1.build();
		assertEquals("http", result1.getScheme());
		assertEquals("e1.com", result1.getHost());
		assertEquals("/p1/ps1", result1.getPath());
		assertEquals("q1", result1.getQuery());
		assertEquals("f1", result1.getFragment());

		UriComponents result2 = builder2.build();
		assertEquals("https", result2.getScheme());
		assertEquals("e2.com", result2.getHost());
		assertEquals("/p1/ps1/p2/ps2", result2.getPath());
		assertEquals("q1&q2", result2.getQuery());
		assertEquals("f2", result2.getFragment());
	}
