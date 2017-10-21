	private void checkLowerItemRelationsAreDeleted() {
		doInJPA( this::entityManagerFactory, entityManager -> {

			Item item = entityManager.createQuery( "select x from Item x where x.code = 'first'", Item.class )
					.getSingleResult();

			Set<ItemRelation> lowerItemRelations = item.getLowerItemRelations();
			Hibernate.initialize( lowerItemRelations );

			Assert.assertEquals( "The collection should be empty", 0, lowerItemRelations.size() );
		} );
	}
