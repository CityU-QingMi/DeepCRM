	@Test
	public void queryParams() throws URISyntaxException {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		UriComponents result = builder.queryParam("baz", "qux", 42).build();

		assertEquals("baz=qux&baz=42", result.getQuery());
		MultiValueMap<String, String> expectedQueryParams = new LinkedMultiValueMap<>(2);
		expectedQueryParams.add("baz", "qux");
		expectedQueryParams.add("baz", "42");
		assertEquals(expectedQueryParams, result.getQueryParams());
	}
