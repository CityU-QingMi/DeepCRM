	@Test
	public void handleMatchMatrixVariablesDecoding() throws Exception {
		MockServerHttpRequest request = method(HttpMethod.GET, URI.create("/path;mvar=a%2fb")).build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		handleMatch(exchange, "/{filter}");

		MultiValueMap<String, String> matrixVariables = getMatrixVariables(exchange, "filter");
		Map<String, String> uriVariables = getUriTemplateVariables(exchange);

		assertNotNull(matrixVariables);
		assertEquals(Collections.singletonList("a/b"), matrixVariables.get("mvar"));
		assertEquals("path", uriVariables.get("filter"));
	}
