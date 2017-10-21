	@Test
	public void statusSerius4xx() throws Exception {
		StatusAssertions assertions = statusAssertions(HttpStatus.BAD_REQUEST);

		// Success
		assertions.is4xxClientError();

		try {
			assertions.is2xxSuccessful();
			fail("Wrong series expected");
		}
		catch (AssertionError error) {
			// Expected
		}
	}
