	@Test
	@TestForIssue( jiraKey = "" )
	@FailureExpected( jiraKey = "" )
	public void testManyToOne() throws Exception {
		Session session = openSession();
		session.beginTransaction();
		try {
			Company company = new Company( 1, "acme" );
			Person person = new Person( 1, "joe", company );
			session.persist( person );
			session.flush();

			company = person.employer;

			session.delete( company );
			session.delete( person );
			session.flush();

			session.persist( person );
			session.flush();

			session.getTransaction().commit();
		}
		catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		session.close();
	}
