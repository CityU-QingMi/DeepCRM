	@Test
	public void isEqualTo() throws Exception {

		StatusAssertions assertions = statusAssertions(HttpStatus.CONFLICT);

		// Success
		assertions.isEqualTo(HttpStatus.CONFLICT);
		assertions.isEqualTo(409);

		try {
			assertions.isEqualTo(HttpStatus.REQUEST_TIMEOUT);
			fail("Wrong status expected");
		}
		catch (AssertionError error) {
			// Expected
		}

		try {
			assertions.isEqualTo(408);
			fail("Wrong status value expected");
		}
		catch (AssertionError error) {
			// Expected
		}
	}
