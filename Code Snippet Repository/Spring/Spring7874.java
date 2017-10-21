	@Test
	public void testGetReferenceWhenNoRow() {
		try {
			Person notThere = sharedEntityManager.getReference(Person.class, 666);

			// We may get here (as with Hibernate).
			// Either behaviour is valid: throw exception on first access
			// or on getReference itself.
			notThere.getFirstName();
			fail("Should have thrown an EntityNotFoundException");
		}
		catch (EntityNotFoundException ex) {
			// expected
		}
	}
