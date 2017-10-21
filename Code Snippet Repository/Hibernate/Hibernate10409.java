	@Test
	public void testCacheFindAfterCollectionAccessRev1() {
		SetRefEdEntity ed1_rev1 = getAuditReader().find( SetRefEdEntity.class, ed1_id, 1 );

		// Reading the collection
		assert ed1_rev1.getReffering().size() == 2;

		SetRefIngEntity ing1_rev1 = getAuditReader().find( SetRefIngEntity.class, ing1_id, 1 );
		SetRefIngEntity ing2_rev1 = getAuditReader().find( SetRefIngEntity.class, ing2_id, 1 );

		for ( SetRefIngEntity setRefIngEntity : ed1_rev1.getReffering() ) {
			assert setRefIngEntity == ing1_rev1 || setRefIngEntity == ing2_rev1;
		}
	}
