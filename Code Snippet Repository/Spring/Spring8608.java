	@Test
	public void performGetManyTimes() throws Exception {

		String responseBody = "{\"name\" : \"Ludwig van Beethoven\", \"someDouble\" : \"1.6035\"}";

		this.mockServer.expect(manyTimes(), requestTo("/composers/42")).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

		@SuppressWarnings("unused")
		ListenableFuture<ResponseEntity<Person>> ludwig =
				this.restTemplate.getForEntity("/composers/{id}", Person.class, 42);

		// We are only validating the request. The response is mocked out.
		// person.getName().equals("Ludwig van Beethoven")
		// person.getDouble().equals(1.6035)

		this.restTemplate.getForEntity("/composers/{id}", Person.class, 42);
		this.restTemplate.getForEntity("/composers/{id}", Person.class, 42);
		this.restTemplate.getForEntity("/composers/{id}", Person.class, 42);
		this.restTemplate.getForEntity("/composers/{id}", Person.class, 42);

		this.mockServer.verify();
	}
