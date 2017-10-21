	@Test
	public void testTrackAddedTwoEntities() {
		StrTestEntity ste1 = new StrTestEntity( "x", steId1 );
		StrTestEntity ste2 = new StrTestEntity( "y", steId2 );

		Assert.assertEquals(
				TestTools.makeSet( ste1, ste2 ),
				new HashSet<Object>( getCrossTypeRevisionChangesReader().findEntities( 1 ) )
		);
	}
