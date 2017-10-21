	@Test
	public void performGetWithResponseBodyFromFile() throws Exception {

		Resource responseBody = new ClassPathResource("ludwig.json", this.getClass());

		this.mockServer.expect(requestTo("/composers/42")).andExpect(method(HttpMethod.GET))
			.andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

		@SuppressWarnings("unused")
		ListenableFuture<ResponseEntity<Person>> ludwig =
				this.restTemplate.getForEntity("/composers/{id}", Person.class, 42);

		// hotel.getId() == 42
		// hotel.getName().equals("Holiday Inn")

		this.mockServer.verify();
	}
