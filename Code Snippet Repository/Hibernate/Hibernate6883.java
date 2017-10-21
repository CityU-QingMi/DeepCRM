	@Test
	public void testSavingEntitiesWithANullOneToOneAssociationValue() {
		doInHibernate( this::sessionFactory, session -> {
			Show show = new Show();
			session.save( show );
		} );

		doInHibernate( this::sessionFactory, session -> {
			ShowDescription showDescription = new ShowDescription();
			session.save( showDescription );
		} );
	}
