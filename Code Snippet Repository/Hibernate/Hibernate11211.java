	@Test
	public void testFindChangedEntitiesByRevisionTypeADD() {
		StrTestEntity ste = new StrTestEntity( "x", steId );
		StrIntTestEntity site = new StrIntTestEntity( "y", 1, siteId );

		assert TestTools.checkCollection(
				getCrossTypeRevisionChangesReader().findEntities( 1, RevisionType.ADD ),
				ste,
				site
		);
	}
