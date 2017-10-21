	@Test
	@TestForIssue(jiraKey = "")
	public void testSessionCacheClear() {
		Session session = openSession();
		session.getTransaction().begin();
		StrTestEntity ste = new StrTestEntity( "data" );
		session.persist( ste );
		session.getTransaction().commit();
		checkEmptyAuditSessionCache( session, "org.hibernate.envers.test.entities.StrTestEntity_AUD" );
	}
