	@Test
	public void unexpectedRequest() throws Exception {
		try {
			this.manager.validateRequest(createRequest(GET, "/foo"));
		}
		catch (AssertionError error) {
			assertEquals("No further requests expected: HTTP GET /foo\n" +
					"0 request(s) executed.\n", error.getMessage());
		}
	}
