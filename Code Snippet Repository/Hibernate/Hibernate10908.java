	@Test
	public void testHistoryOfEdIng2() {
		SetRefEdEntity ed1 = getEntityManager().find( SetRefEdEntity.class, ed1_id );
		SetRefEdEntity ed2 = getEntityManager().find( SetRefEdEntity.class, ed2_id );

		SetRefIngEntity rev1 = getAuditReader().find( SetRefIngEntity.class, ing2_id, 1 );
		SetRefIngEntity rev2 = getAuditReader().find( SetRefIngEntity.class, ing2_id, 2 );
		SetRefIngEntity rev3 = getAuditReader().find( SetRefIngEntity.class, ing2_id, 3 );
		SetRefIngEntity rev4 = getAuditReader().find( SetRefIngEntity.class, ing2_id, 4 );

		assert rev1 == null;
		assert rev2.getReference().equals( ed1 );
		assert rev3.getReference().equals( ed1 );
		assert rev4.getReference().equals( ed2 );
	}
