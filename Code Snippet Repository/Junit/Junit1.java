	@Test
	void dependentAssertions() {
		// Within a code block, if an assertion fails the
		// subsequent code in the same block will be skipped.
		assertAll("properties",
			() -> {
				String firstName = person.getFirstName();
				assertNotNull(firstName);

				// Executed only if the previous assertion is valid.
				assertAll("first name",
					() -> assertTrue(firstName.startsWith("J")),
					() -> assertTrue(firstName.endsWith("n"))
				);
			},
			() -> {
				// Grouped assertion, so processed independently
				// of results of first name assertions.
				String lastName = person.getLastName();
				assertNotNull(lastName);

				// Executed only if the previous assertion is valid.
				assertAll("last name",
					() -> assertTrue(lastName.startsWith("D")),
					() -> assertTrue(lastName.endsWith("e"))
				);
			}
		);
	}
