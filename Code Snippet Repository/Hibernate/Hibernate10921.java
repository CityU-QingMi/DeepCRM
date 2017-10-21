	@Test
	public void testHistoryOfEdId2() {
		SetRefIngEntity ing3 = getEntityManager().find( SetRefIngEntity.class, ing3_id );

		SetRefEdEntity rev1 = getAuditReader().find( SetRefEdEntity.class, ed2_id, 1 );
		SetRefEdEntity rev2 = getAuditReader().find( SetRefEdEntity.class, ed2_id, 2 );
		SetRefEdEntity rev3 = getAuditReader().find( SetRefEdEntity.class, ed2_id, 3 );
		SetRefEdEntity rev4 = getAuditReader().find( SetRefEdEntity.class, ed2_id, 4 );
		SetRefEdEntity rev5 = getAuditReader().find( SetRefEdEntity.class, ed2_id, 5 );

		assert rev1.getReffering().equals( Collections.EMPTY_SET );
		assert rev2.getReffering().equals( Collections.EMPTY_SET );
		assert rev3.getReffering().equals( Collections.EMPTY_SET );
		assert rev4.getReffering().equals( makeSet( ing3 ) );
		assert rev5.getReffering().equals( makeSet( ing3 ) );
	}
