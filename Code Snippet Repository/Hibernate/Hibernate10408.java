	@Test
	public void testCacheReferenceAccessAfterCollectionAccessRev1() {
		SetRefEdEntity ed1_rev1 = getAuditReader().find( SetRefEdEntity.class, ed1_id, 1 );

		// It should be exactly the same object
		assert ed1_rev1.getReffering().size() == 2;
		for ( SetRefIngEntity setRefIngEntity : ed1_rev1.getReffering() ) {
			assert setRefIngEntity.getReference() == ed1_rev1;
		}
	}
