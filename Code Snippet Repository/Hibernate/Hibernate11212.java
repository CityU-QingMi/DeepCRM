	@Test
	public void testFindChangedEntitiesByRevisionTypeDEL() {
		StrTestEntity ste = new StrTestEntity( null, steId );
		StrIntTestEntity site = new StrIntTestEntity( null, null, siteId );

		assert TestTools.checkCollection(
				getCrossTypeRevisionChangesReader().findEntities( 3, RevisionType.DEL ),
				ste,
				site
		);
	}
