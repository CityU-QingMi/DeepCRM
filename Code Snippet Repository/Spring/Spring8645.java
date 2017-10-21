	@Test
	public void reasonEquals() throws Exception {

		StatusAssertions assertions = statusAssertions(HttpStatus.CONFLICT);

		// Success
		assertions.reasonEquals("Conflict");

		try {
			assertions.reasonEquals("Request Timeout");
			fail("Wrong reason expected");
		}
		catch (AssertionError error) {
			// Expected
		}
	}
