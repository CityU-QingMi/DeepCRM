	@Test(expected = AssertionError.class)
	public void expectNeverViolated() throws Exception {

		String responseBody = "{\"name\" : \"Ludwig van Beethoven\", \"someDouble\" : \"1.6035\"}";

		this.mockServer.expect(once(), requestTo("/composers/42")).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));
		this.mockServer.expect(never(), requestTo("/composers/43")).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

		this.restTemplate.getForObject("/composers/{id}", Person.class, 42);
		this.restTemplate.getForObject("/composers/{id}", Person.class, 43);
	}
