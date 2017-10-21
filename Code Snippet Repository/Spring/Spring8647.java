	@Test
	public void statusSerius2xx() throws Exception {
		StatusAssertions assertions = statusAssertions(HttpStatus.OK);

		// Success
		assertions.is2xxSuccessful();

		try {
			assertions.is5xxServerError();
			fail("Wrong series expected");
		}
		catch (AssertionError error) {
			// Expected
		}
	}
