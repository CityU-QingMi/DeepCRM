	@Test
	public void testHistoryOfEdId2() {
		CollectionRefIngEntity ing1 = getEntityManager().find( CollectionRefIngEntity.class, ing1_id );
		CollectionRefIngEntity ing2 = getEntityManager().find( CollectionRefIngEntity.class, ing2_id );

		CollectionRefEdEntity rev1 = getAuditReader().find( CollectionRefEdEntity.class, ed2_id, 1 );
		CollectionRefEdEntity rev2 = getAuditReader().find( CollectionRefEdEntity.class, ed2_id, 2 );
		CollectionRefEdEntity rev3 = getAuditReader().find( CollectionRefEdEntity.class, ed2_id, 3 );

		assert rev1.getReffering().containsAll( Collections.EMPTY_SET );
		assert rev1.getReffering().size() == 0;

		assert rev2.getReffering().containsAll( makeSet( ing1 ) );
		assert rev2.getReffering().size() == 1;

		assert rev3.getReffering().containsAll( makeSet( ing1, ing2 ) );
		assert rev3.getReffering().size() == 2;

	}
