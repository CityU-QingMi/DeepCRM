	@Test
	@TestForIssue( jiraKey = "")
	public void testTreatedJoinInWhereClause() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Bid> query = cb.createQuery( Bid.class );
			Root<Bid> bid = query.from( Bid.class );

			final Join<Bid, Book> item = bid.join( "item" );
			Join<Bid, Book> book = cb.treat( item, Book.class );
			query.where( cb.equal( book.get("title"), "La moneta di Akragas" ) );

			final List<Bid> resultList = entityManager.createQuery( query ).getResultList();
			assertThat(resultList.size(),is(1));
		} );
	}
