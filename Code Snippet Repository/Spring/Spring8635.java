	@Test
	public void valueEqualsWithMultipeValues() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("foo", "bar");
		headers.add("foo", "baz");
		HeaderAssertions assertions = headerAssertions(headers);

		// Success
		assertions.valueEquals("foo", "bar", "baz");

		try {
			assertions.valueEquals("foo", "bar", "what?!");
			fail("Wrong value expected");
		}
		catch (AssertionError error) {
			// expected
		}

		try {
			assertions.valueEquals("foo", "bar");
			fail("Too few values expected");
		}
		catch (AssertionError error) {
			// expected
		}

	}
