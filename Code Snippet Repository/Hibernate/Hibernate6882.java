	@Test
	public void testSavingEntitiesWithANullOneToOneAssociationValue() {
		doInHibernate( this::sessionFactory, session -> {
			Show show = new Show();
			session.save( show );
		} );

		try {
			doInHibernate( this::sessionFactory, session -> {
				ShowDescription showDescription = new ShowDescription();
				session.save( showDescription );
			} );
			fail();
		}
		catch (PropertyValueException expected) {
			assertTrue( expected.getMessage().startsWith( "not-null property references a null or transient value" ) );
		}
	}
