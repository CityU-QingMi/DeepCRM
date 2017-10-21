	@Test
	public void testHistoryOfEdIng1() {
		SetRefEdEmbIdEntity ed1 = getEntityManager().find( SetRefEdEmbIdEntity.class, ed1_id );
		SetRefEdEmbIdEntity ed2 = getEntityManager().find( SetRefEdEmbIdEntity.class, ed2_id );

		SetRefIngEmbIdEntity rev1 = getAuditReader().find( SetRefIngEmbIdEntity.class, ing1_id, 1 );
		SetRefIngEmbIdEntity rev2 = getAuditReader().find( SetRefIngEmbIdEntity.class, ing1_id, 2 );
		SetRefIngEmbIdEntity rev3 = getAuditReader().find( SetRefIngEmbIdEntity.class, ing1_id, 3 );

		assert rev1.getReference().equals( ed1 );
		assert rev2.getReference().equals( ed2 );
		assert rev3.getReference().equals( ed2 );
	}
