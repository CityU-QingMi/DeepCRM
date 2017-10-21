	@Test
	public void testHistoryOfEdIng2() {
		StrTestEntity ed1 = getEntityManager().find( StrTestEntity.class, ed1_id );
		StrTestEntity ed2 = getEntityManager().find( StrTestEntity.class, ed2_id );

		SetUniEntity rev1 = getAuditReader().find( SetUniEntity.class, ing2_id, 1 );
		SetUniEntity rev2 = getAuditReader().find( SetUniEntity.class, ing2_id, 2 );
		SetUniEntity rev3 = getAuditReader().find( SetUniEntity.class, ing2_id, 3 );
		SetUniEntity rev4 = getAuditReader().find( SetUniEntity.class, ing2_id, 4 );
		SetUniEntity rev5 = getAuditReader().find( SetUniEntity.class, ing2_id, 5 );

		assert rev1.getReferences().equals( Collections.EMPTY_SET );
		assert rev2.getReferences().equals( TestTools.makeSet( ed1, ed2 ) );
		assert rev3.getReferences().equals( TestTools.makeSet( ed1, ed2 ) );
		assert rev4.getReferences().equals( TestTools.makeSet( ed1, ed2 ) );
		assert rev5.getReferences().equals( TestTools.makeSet( ed1, ed2 ) );
	}
