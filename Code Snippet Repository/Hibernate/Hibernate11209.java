	@Test
	public void testTrackAddedEntitiesGroupByRevisionType() {
		StrTestEntity ste = new StrTestEntity( "x", steId );
		StrIntTestEntity site = new StrIntTestEntity( "y", 1, siteId );

		Map<RevisionType, List<Object>> result = getCrossTypeRevisionChangesReader().findEntitiesGroupByRevisionType( 1 );
		assert TestTools.checkCollection( result.get( RevisionType.ADD ), site, ste );
		assert TestTools.checkCollection( result.get( RevisionType.MOD ) );
		assert TestTools.checkCollection( result.get( RevisionType.DEL ) );
	}
