	@Test
	public void testHasChangedMapEntity() throws Exception {
		List list = queryForPropertyHasChanged( ComponentMapKeyEntity.class, cmke_id, "idmap" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 2 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				ComponentMapKeyEntity.class,
				cmke_id, "idmap"
		);
		assertEquals( 0, list.size() );
	}
