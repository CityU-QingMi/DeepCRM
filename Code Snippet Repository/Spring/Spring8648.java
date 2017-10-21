	@Test
	public void statusSerius3xx() throws Exception {
		StatusAssertions assertions = statusAssertions(HttpStatus.PERMANENT_REDIRECT);

		// Success
		assertions.is3xxRedirection();

		try {
			assertions.is2xxSuccessful();
			fail("Wrong series expected");
		}
		catch (AssertionError error) {
			// Expected
		}
	}
