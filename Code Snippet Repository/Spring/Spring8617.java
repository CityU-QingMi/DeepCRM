	@Test
	public void contentTypeNoMatch() throws Exception {
		this.mockServer.expect(content().contentType("application/json;charset=UTF-8")).andRespond(withSuccess());
		try {
			this.restTemplate.put(new URI("/foo"), "foo");
		}
		catch (AssertionError error) {
			String message = error.getMessage();
			assertTrue(message, message.startsWith("Content type expected:<application/json;charset=UTF-8>"));
		}
	}
