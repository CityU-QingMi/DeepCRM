	@Test
	public void testFindEntityTypesChangedInRevision() {
		assert TestTools.makeSet(
				Pair.make( StrTestEntity.class.getName(), StrTestEntity.class ),
				Pair.make( StrIntTestEntity.class.getName(), StrIntTestEntity.class )
		)
				.equals( getCrossTypeRevisionChangesReader().findEntityTypes( 1 ) );

		assert TestTools.makeSet( Pair.make( StrIntTestEntity.class.getName(), StrIntTestEntity.class ) )
				.equals( getCrossTypeRevisionChangesReader().findEntityTypes( 2 ) );

		assert TestTools.makeSet(
				Pair.make( StrTestEntity.class.getName(), StrTestEntity.class ),
				Pair.make( StrIntTestEntity.class.getName(), StrIntTestEntity.class )
		)
				.equals( getCrossTypeRevisionChangesReader().findEntityTypes( 3 ) );
	}
