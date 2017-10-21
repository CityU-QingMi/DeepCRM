	@Test
	public void sessionCountClosetShouldBeIncrementedWhenTheEntityManagerIsClosed() {
		final SessionFactoryImplementor entityManagerFactory = entityManagerFactory();
		final Statistics statistics = entityManagerFactory.unwrap( SessionFactory.class ).getStatistics();
		EntityManager em = createEntityManager();
		assertThat( "The session close count should be zero", statistics.getSessionCloseCount(), is( 0L ) );

		em.close();

		assertThat( "The session close count was not incremented", statistics.getSessionCloseCount(), is( 1L ) );
	}
