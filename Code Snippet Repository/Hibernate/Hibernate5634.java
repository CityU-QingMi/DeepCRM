	@Test
	@TestForIssue(jiraKey = "")
	public void testJoinMethodOnATreatedJoin() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Bid> query = cb.createQuery( Bid.class );
			Root<Bid> bid = query.from( Bid.class );

			final Join<Bid, Book> item = bid.join( "item" );
			final Join<Object, Object> price = item.join( "price" );
			Join<Bid, Book> book = cb.treat( item, Book.class );

			Join<Book, Author> owner = book.join( "author" );
			query.select( owner.get( "name" ) );

			query.where( cb.equal( price.get("amount"), 10 ) );

			final List<Bid> resultList = entityManager.createQuery( query ).getResultList();
			assertThat(resultList.size(),is(2));
		} );
	}
