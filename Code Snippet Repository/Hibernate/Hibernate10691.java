	@Test
	public void testHistoryOfEdIng1() {
		SetRefEdEntity ed1 = getEntityManager().find( SetRefEdEntity.class, ed1_id );
		SetRefEdEntity ed2 = getEntityManager().find( SetRefEdEntity.class, ed2_id );

		SetRefIngEntity rev1 = getAuditReader().find( SetRefIngEntity.class, ing1_id, 1 );
		SetRefIngEntity rev2 = getAuditReader().find( SetRefIngEntity.class, ing1_id, 2 );
		SetRefIngEntity rev3 = getAuditReader().find( SetRefIngEntity.class, ing1_id, 3 );

		assert rev1 == null;
		assert rev2.getReference().equals( ed1 );
		assert rev3.getReference().equals( ed2 );
	}
