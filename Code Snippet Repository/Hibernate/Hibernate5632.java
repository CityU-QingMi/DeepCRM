	@Test
	@TestForIssue(jiraKey = "")
	public void testTreatJoin() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Bid> query = cb.createQuery( Bid.class );
			Root<Bid> bid = query.from( Bid.class );

			Join<Bid, Book> book = cb.treat( bid.join( "item" ), Book.class );
			query.select( book.get( "title" ) );

			final List<Bid> resultList = entityManager.createQuery( query ).getResultList();
			assertThat(resultList.size(),is(2));
		} );
	}
