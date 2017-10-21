	@Test
	public void testHistoryOfEdIng1() {
		MapOwnedEntity ed1 = getEntityManager().find( MapOwnedEntity.class, ed1_id );

		MapOwningEntity rev1 = getAuditReader().find( MapOwningEntity.class, ing1_id, 1 );
		MapOwningEntity rev2 = getAuditReader().find( MapOwningEntity.class, ing1_id, 2 );
		MapOwningEntity rev3 = getAuditReader().find( MapOwningEntity.class, ing1_id, 3 );

		assert rev1.getReferences().equals( Collections.EMPTY_MAP );
		assert rev2.getReferences().equals( TestTools.makeMap( "1", ed1, "2", ed1 ) );
		assert rev3.getReferences().equals( Collections.EMPTY_MAP );
	}
