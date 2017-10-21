	@Test
	public void handleMatchMatrixVariables() throws Exception {
		MultiValueMap<String, String> matrixVariables;
		Map<String, String> uriVariables;

		ServerWebExchange exchange = MockServerWebExchange.from(get("/cars;colors=red,blue,green;year=2012").build());
		handleMatch(exchange, "/{cars}");

		matrixVariables = getMatrixVariables(exchange, "cars");
		uriVariables = getUriTemplateVariables(exchange);

		assertNotNull(matrixVariables);
		assertEquals(Arrays.asList("red", "blue", "green"), matrixVariables.get("colors"));
		assertEquals("2012", matrixVariables.getFirst("year"));
		assertEquals("cars", uriVariables.get("cars"));
	}
