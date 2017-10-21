	@Test
	public void performGet() throws Exception {

		String responseBody = "{\"name\" : \"Ludwig van Beethoven\", \"someDouble\" : \"1.6035\"}";

		this.mockServer.expect(requestTo("/composers/42")).andExpect(method(HttpMethod.GET))
			.andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

		@SuppressWarnings("unused")
		Person ludwig = this.restTemplate.getForObject("/composers/{id}", Person.class, 42);

		// We are only validating the request. The response is mocked out.
		// hotel.getId() == 42
		// hotel.getName().equals("Holiday Inn")

		this.mockServer.verify();
	}
