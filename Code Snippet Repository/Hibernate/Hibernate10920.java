	@Test
	public void testHistoryOfEdId1() {
		SetRefIngEntity ing1 = getEntityManager().find( SetRefIngEntity.class, ing1_id );
		SetRefIngEntity ing2 = new SetRefIngEntity( 4, "data_ing_2", new SetRefEdEntity( 1, "data_ed_1" ) );
		SetRefIngEntity ing3 = getEntityManager().find( SetRefIngEntity.class, ing3_id );
		SetRefIngEntity ing4 = getEntityManager().find( SetRefIngEntity.class, ing4_id );

		SetRefEdEntity rev1 = getAuditReader().find( SetRefEdEntity.class, ed1_id, 1 );
		SetRefEdEntity rev2 = getAuditReader().find( SetRefEdEntity.class, ed1_id, 2 );
		SetRefEdEntity rev3 = getAuditReader().find( SetRefEdEntity.class, ed1_id, 3 );
		SetRefEdEntity rev4 = getAuditReader().find( SetRefEdEntity.class, ed1_id, 4 );
		SetRefEdEntity rev5 = getAuditReader().find( SetRefEdEntity.class, ed1_id, 5 );

		assert rev1.getReffering().equals( makeSet( ing1, ing2, ing3, ing4 ) );
		assert rev2.getReffering().equals( makeSet( ing2, ing3, ing4 ) );
		assert rev3.getReffering().equals( makeSet( ing3, ing4 ) );
		assert rev4.getReffering().equals( makeSet( ing4 ) );
		assert rev5 == null;
	}
