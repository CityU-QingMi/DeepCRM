	@Test
	public void encodingNone() throws Exception {
		DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
		factory.setEncodingMode(EncodingMode.NONE);
		UriBuilder uriBuilder = factory.uriString("/foo/a%2Fb/{id}");

		String id = "c%2Fd";
		String expected = "/foo/a%2Fb/c%2Fd";

		assertEquals(expected, uriBuilder.build(id).toString());
		assertEquals(expected, uriBuilder.build(singletonMap("id", id)).toString());
	}
