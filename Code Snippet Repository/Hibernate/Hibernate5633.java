	@Test
	@TestForIssue(jiraKey = "")
	public void testTreatJoin2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Bid> query = cb.createQuery( Bid.class );
			Root<Bid> bid = query.from( Bid.class );

			cb.treat( bid.join( "item" ), Book.class );
			cb.treat( bid.join( "item" ), Car.class );

			query.select( bid );

			final List<Bid> resultList = entityManager.createQuery( query ).getResultList();
			assertThat(resultList.size(),is(2));
		} );
	}
