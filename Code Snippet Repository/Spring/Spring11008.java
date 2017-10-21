	@Test
	public void emptyQueryParam() throws URISyntaxException {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		UriComponents result = builder.queryParam("baz").build();

		assertEquals("baz", result.getQuery());
		MultiValueMap<String, String> expectedQueryParams = new LinkedMultiValueMap<>(2);
		expectedQueryParams.add("baz", null);
		assertEquals(expectedQueryParams, result.getQueryParams());
	}
