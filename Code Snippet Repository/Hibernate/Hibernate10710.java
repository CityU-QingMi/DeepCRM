	@Test
	public void testHistoryOfEdIng2() {
		MapOwnedEntity ed1 = getEntityManager().find( MapOwnedEntity.class, ed1_id );
		MapOwnedEntity ed2 = getEntityManager().find( MapOwnedEntity.class, ed2_id );

		MapOwningEntity rev1 = getAuditReader().find( MapOwningEntity.class, ing2_id, 1 );
		MapOwningEntity rev2 = getAuditReader().find( MapOwningEntity.class, ing2_id, 2 );
		MapOwningEntity rev3 = getAuditReader().find( MapOwningEntity.class, ing2_id, 3 );

		assert rev1.getReferences().equals( TestTools.makeMap( "2", ed2 ) );
		assert rev2.getReferences().equals( TestTools.makeMap( "2", ed1 ) );
		assert rev3.getReferences().equals( TestTools.makeMap( "1", ed2 ) );
	}
