	@Test
	@TestForIssue(jiraKey = "")
	@RequiresDialect( SQLServerDialect.class )
	public void testCriteriaWithPessimisticLock() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Person> criteria = builder.createQuery( Person.class );
			Root<Person> personRoot = criteria.from( Person.class );
			ParameterExpression<Long> personIdParameter = builder.parameter( Long.class );

			// Eagerly fetch the parent
			personRoot.fetch( "parent", JoinType.LEFT );

			criteria.select( personRoot )
					.where( builder.equal( personRoot.get( "id" ), personIdParameter ) );

			final List<Person> resultList = entityManager.createQuery( criteria )
					.setParameter( personIdParameter, 1L )
					.setLockMode( LockModeType.PESSIMISTIC_WRITE )
					.getResultList();

			resultList.isEmpty();

		} );
	}
