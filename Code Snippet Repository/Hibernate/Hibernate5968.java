	@Test
	@TestForIssue(jiraKey = "")
	@FailureExpected( jiraKey = "" )
	public void testInitializingSecondCollection() {
		doInJPA( this::entityManagerFactory, entityManager -> {

			Item item = entityManager.createQuery( "select x from Item x where x.code = 'first'", Item.class )
					.getSingleResult();

			Set<ItemRelation> lowerItemRelations = item.getLowerItemRelations();
			Hibernate.initialize( lowerItemRelations );

			Set<ItemRelation> higherItemRelations = item.getHigherItemRelations();
			Hibernate.initialize( higherItemRelations );

			Assert.assertEquals( 1, lowerItemRelations.size() );

			lowerItemRelations.clear();
		} );
		checkLowerItemRelationsAreDeleted();
	}
