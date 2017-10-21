	@Test
	public void verify() {

		this.mockServer.expect(requestTo("/number")).andExpect(method(HttpMethod.GET))
			.andRespond(withSuccess("1", MediaType.TEXT_PLAIN));

		this.mockServer.expect(requestTo("/number")).andExpect(method(HttpMethod.GET))
			.andRespond(withSuccess("2", MediaType.TEXT_PLAIN));

		this.mockServer.expect(requestTo("/number")).andExpect(method(HttpMethod.GET))
			.andRespond(withSuccess("4", MediaType.TEXT_PLAIN));

		this.mockServer.expect(requestTo("/number")).andExpect(method(HttpMethod.GET))
			.andRespond(withSuccess("8", MediaType.TEXT_PLAIN));

		@SuppressWarnings("unused")
		String result1 = this.restTemplate.getForObject("/number", String.class);
		// result1 == "1"

		@SuppressWarnings("unused")
		String result2 = this.restTemplate.getForObject("/number", String.class);
		// result == "2"

		try {
			this.mockServer.verify();
		}
		catch (AssertionError error) {
			assertTrue(error.getMessage(), error.getMessage().contains("2 unsatisfied expectation(s)"));
		}
	}
