	@Test
	public void valueEquals() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("foo", "bar");
		HeaderAssertions assertions = headerAssertions(headers);

		// Success
		assertions.valueEquals("foo", "bar");

		try {
			assertions.valueEquals("what?!", "bar");
			fail("Missing header expected");
		}
		catch (AssertionError error) {
			// expected
		}

		try {
			assertions.valueEquals("foo", "what?!");
			fail("Wrong value expected");
		}
		catch (AssertionError error) {
			// expected
		}

		try {
			assertions.valueEquals("foo", "bar", "what?!");
			fail("Wrong # of values expected");
		}
		catch (AssertionError error) {
			// expected
		}
	}
