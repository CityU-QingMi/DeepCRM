	@Test
	public void testTrackDeletedEntitiesGroupByRevisionType() {
		StrTestEntity ste = new StrTestEntity( null, steId );
		StrIntTestEntity site = new StrIntTestEntity( null, null, siteId );

		Map<RevisionType, List<Object>> result = getCrossTypeRevisionChangesReader().findEntitiesGroupByRevisionType( 3 );
		assert TestTools.checkCollection( result.get( RevisionType.ADD ) );
		assert TestTools.checkCollection( result.get( RevisionType.MOD ) );
		assert TestTools.checkCollection( result.get( RevisionType.DEL ), site, ste );
	}
