	@Test
	@TestForIssue(jiraKey = "")
	public void testTrimAChar() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			final CriteriaQuery<Customer> query = criteriaBuilder.createQuery( Customer.class );
			final Root<Customer> from = query.from( Customer.class );
			query.select( from );

			query.where( criteriaBuilder.equal( criteriaBuilder.trim(
					CriteriaBuilder.Trimspec.LEADING,
					criteriaBuilder.literal( 'R' ),
					from.get( "name" )
			), " Vincent" ) );
			List<Customer> resultList = entityManager.createQuery( query ).getResultList();
			assertThat( resultList.size(), is( 1 ) );
		} );
	}
