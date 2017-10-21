	@Test
	@SuppressWarnings("")
	public void testModifiedEntityTypes() {
		assert TestTools.makeSet(
				Pair.make( Car.class.getName(), Car.class ),
				Pair.make( "Personaje", Person.class )
		)
				.equals( getAuditReader().getCrossTypeRevisionChangesReader().findEntityTypes( 1 ) );
		assert TestTools.makeSet(
				Pair.make( Car.class.getName(), Car.class ),
				Pair.make( "Personaje", Person.class )
		)
				.equals( getAuditReader().getCrossTypeRevisionChangesReader().findEntityTypes( 2 ) );
	}
