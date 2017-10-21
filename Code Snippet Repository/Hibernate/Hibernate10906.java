	@Test
	public void testHistoryOfEdId2() {
		SetRefIngEntity ing1 = getEntityManager().find( SetRefIngEntity.class, ing1_id );
		SetRefIngEntity ing2 = getEntityManager().find( SetRefIngEntity.class, ing2_id );

		SetRefEdEntity rev1 = getAuditReader().find( SetRefEdEntity.class, ed2_id, 1 );
		SetRefEdEntity rev2 = getAuditReader().find( SetRefEdEntity.class, ed2_id, 2 );
		SetRefEdEntity rev3 = getAuditReader().find( SetRefEdEntity.class, ed2_id, 3 );
		SetRefEdEntity rev4 = getAuditReader().find( SetRefEdEntity.class, ed2_id, 4 );

		assert rev1.getReffering().equals( Collections.EMPTY_SET );
		assert rev2.getReffering().equals( Collections.EMPTY_SET );
		assert rev3.getReffering().equals( TestTools.makeSet( ing1 ) );
		assert rev4.getReffering().equals( TestTools.makeSet( ing1, ing2 ) );
	}
