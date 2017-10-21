	@Test
	public void statusSerius1xx() throws Exception {
		StatusAssertions assertions = statusAssertions(HttpStatus.CONTINUE);

		// Success
		assertions.is1xxInformational();

		try {
			assertions.is2xxSuccessful();
			fail("Wrong series expected");
		}
		catch (AssertionError error) {
			// Expected
		}
	}
