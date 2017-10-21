	@Test
	public void sequentialRequestsTooFew() throws Exception {
		this.manager.expectRequest(min(1), requestTo("/foo")).andExpect(method(GET)).andRespond(withSuccess());
		this.manager.expectRequest(min(1), requestTo("/bar")).andExpect(method(GET)).andRespond(withSuccess());

		this.thrown.expectMessage("Further request(s) expected leaving 1 unsatisfied expectation(s).\n" +
				"1 request(s) executed:\nGET /foo\n");

		this.manager.validateRequest(createRequest(GET, "/foo"));
		this.manager.verify();
	}
