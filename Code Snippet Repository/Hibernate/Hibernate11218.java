	@Test
	public void testTrackUpdateAndRemoveDifferentEntities() {
		StrTestEntity ste1 = new StrTestEntity( "z", steId1 );
		StrTestEntity ste2 = new StrTestEntity( null, steId2 );

		Assert.assertEquals(
				TestTools.makeSet( ste1, ste2 ),
				new HashSet<Object>( getCrossTypeRevisionChangesReader().findEntities( 2 ) )
		);
	}
