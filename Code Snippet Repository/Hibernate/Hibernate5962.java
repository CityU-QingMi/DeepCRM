	@Test
	@TestForIssue( jiraKey = "" )
	@FailureExpected( jiraKey = "" )
	public void testManyToOne() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Company company = new Company( 1, "acme" );
			Person person = new Person( 1, "joe", company );
			em.persist( person );
			em.flush();

			em.remove( company );
			em.remove( person );
			em.flush();

			em.persist( person );
			em.flush();

			em.getTransaction().commit();
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
		em.close();
	}
