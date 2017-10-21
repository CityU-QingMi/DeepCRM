	@Test
	public void statusSerius5xx() throws Exception {
		StatusAssertions assertions = statusAssertions(HttpStatus.INTERNAL_SERVER_ERROR);

		// Success
		assertions.is5xxServerError();

		try {
			assertions.is2xxSuccessful();
			fail("Wrong series expected");
		}
		catch (AssertionError error) {
			// Expected
		}
	}
