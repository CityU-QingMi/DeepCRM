	@Test
	public void testQueryWithDeleted() {
		// Selecting all entities, also the deleted ones
		List entities = getAuditReader().createQuery().forRevisionsOfEntity( PrimitiveTestEntity.class, true, true )
				.getResultList();

		assert entities.size() == 3;
		assert entities.get( 0 ).equals( new PrimitiveTestEntity( id1, 10, 0 ) );
		assert entities.get( 1 ).equals( new PrimitiveTestEntity( id1, 20, 0 ) );
		assert entities.get( 2 ).equals( new PrimitiveTestEntity( id1, 0, 0 ) );
	}
