	@Test
	@TestForIssue( jiraKey = "")
	public void testNullBidirForeignIdGenerator() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Person person = new Person();
		person.setPersonAddress( null );
		try {
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
