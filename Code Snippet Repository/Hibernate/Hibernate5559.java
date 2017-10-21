	@Test
	@TestForIssue( jiraKey = "" )
	public void testUsage() {
		JournalingListener.reset();

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( new MyEntity( 1, "steve" ) );
		em.getTransaction().commit();
		em.close();

		assertEquals( 1, JournalingListener.getPrePersistCount() );

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.createQuery( "delete MyEntity" ).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
