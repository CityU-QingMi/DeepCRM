	@Test
	@TestForIssue(jiraKey = "")
	public void testAnEmptyListIsReturnedWhenSetMaxResultsToZero() {
		TransactionUtil.doInJPA( this::entityManagerFactory, (EntityManager entityManager) -> {
			final CriteriaQuery<Person> query = entityManager.getCriteriaBuilder().createQuery( Person.class );
			query.from( Person.class );
			final List list = entityManager.createQuery( query ).setMaxResults( 0 ).getResultList();
			assertTrue( "The list should be empty with setMaxResults 0", list.isEmpty() );
		} );
	}
