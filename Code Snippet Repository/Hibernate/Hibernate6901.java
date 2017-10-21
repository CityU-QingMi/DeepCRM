	@Test
	@TestForIssue( jiraKey = "")
	public void testNotFoundBidirForeignIdGenerator() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Person person = new Person();
		person.setPersonAddress( null );
		person.setId( 1 );
		try {
			// Hibernate resets the ID to null before executing the foreign generator
			s.persist( person );
			s.flush();
			fail( "should have thrown IdentifierGenerationException.");
		}
		catch (PersistenceException ex) {
			assertTyping(IdentifierGenerationException.class, ex.getCause());
			// expected
		}
		finally {
			tx.rollback();
			s.close();
		}
	}
