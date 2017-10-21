	@Test
	public void valueMatches() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HeaderAssertions assertions = headerAssertions(headers);

		// Success
		assertions.valueMatches("Content-Type", ".*UTF-8.*");

		try {
			assertions.valueMatches("Content-Type", ".*ISO-8859-1.*");
			fail("Wrong pattern expected");
		}
		catch (AssertionError error) {
			Throwable cause = error.getCause();
			assertNotNull(cause);
			assertEquals("Response header [Content-Type]='application/json;charset=UTF-8' " +
					"does not match '.*ISO-8859-1.*'", cause.getMessage());
		}
	}
