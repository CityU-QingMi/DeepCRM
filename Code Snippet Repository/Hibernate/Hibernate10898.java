	@Test
	public void testHistoryOfEdIng1() {
		CollectionRefEdEntity ed1 = getEntityManager().find( CollectionRefEdEntity.class, ed1_id );
		CollectionRefEdEntity ed2 = getEntityManager().find( CollectionRefEdEntity.class, ed2_id );

		CollectionRefIngEntity rev1 = getAuditReader().find( CollectionRefIngEntity.class, ing1_id, 1 );
		CollectionRefIngEntity rev2 = getAuditReader().find( CollectionRefIngEntity.class, ing1_id, 2 );
		CollectionRefIngEntity rev3 = getAuditReader().find( CollectionRefIngEntity.class, ing1_id, 3 );

		assert rev1.getReference().equals( ed1 );
		assert rev2.getReference().equals( ed2 );
		assert rev3.getReference().equals( ed2 );
	}
