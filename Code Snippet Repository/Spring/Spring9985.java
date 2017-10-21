	@Test
	public void uriVariablesExpansion() throws URISyntaxException {
		URI uri = new UriTemplate("http://example.com/{foo}").expand("bar");
		RequestEntity.get(uri).accept(MediaType.TEXT_PLAIN).build();

		String url = "http://www.{host}.com/{path}";
		String host = "example";
		String path = "foo/bar";
		URI expected = new URI("http://www.example.com/foo/bar");

		uri = new UriTemplate(url).expand(host, path);
		RequestEntity<?> entity = RequestEntity.get(uri).build();
		assertEquals(expected, entity.getUrl());

		Map<String, String> uriVariables = new HashMap<>(2);
		uriVariables.put("host", host);
		uriVariables.put("path", path);

		uri = new UriTemplate(url).expand(uriVariables);
		entity = RequestEntity.get(uri).build();
		assertEquals(expected, entity.getUrl());
	}
