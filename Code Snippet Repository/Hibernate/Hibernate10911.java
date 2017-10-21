	@Test
	public void testHistoryOfEdId2() {
		SetRefIngEmbIdEntity ing1 = getEntityManager().find( SetRefIngEmbIdEntity.class, ing1_id );
		SetRefIngEmbIdEntity ing2 = getEntityManager().find( SetRefIngEmbIdEntity.class, ing2_id );

		SetRefEdEmbIdEntity rev1 = getAuditReader().find( SetRefEdEmbIdEntity.class, ed2_id, 1 );
		SetRefEdEmbIdEntity rev2 = getAuditReader().find( SetRefEdEmbIdEntity.class, ed2_id, 2 );
		SetRefEdEmbIdEntity rev3 = getAuditReader().find( SetRefEdEmbIdEntity.class, ed2_id, 3 );

		assert rev1.getReffering().equals( Collections.EMPTY_SET );
		assert rev2.getReffering().equals( makeSet( ing1 ) );
		assert rev3.getReffering().equals( makeSet( ing1, ing2 ) );
	}
