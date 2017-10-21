	@Test
	public void sequentialRequestsTooMany() throws Exception {
		this.manager.expectRequest(max(1), requestTo("/foo")).andExpect(method(GET)).andRespond(withSuccess());
		this.manager.expectRequest(max(1), requestTo("/bar")).andExpect(method(GET)).andRespond(withSuccess());

		this.thrown.expectMessage("No further requests expected: HTTP GET /baz\n" +
				"2 request(s) executed:\n" +
				"GET /foo\n" +
				"GET /bar\n");

		this.manager.validateRequest(createRequest(GET, "/foo"));
		this.manager.validateRequest(createRequest(GET, "/bar"));
		this.manager.validateRequest(createRequest(GET, "/baz"));
	}
