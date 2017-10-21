	@Test
	public void testCacheReferenceAccessAfterFind() {
		SetRefEdEntity ed1_rev1 = getAuditReader().find( SetRefEdEntity.class, ed1_id, 1 );

		SetRefIngEntity ing1_rev1 = getAuditReader().find( SetRefIngEntity.class, ing1_id, 1 );
		SetRefIngEntity ing2_rev1 = getAuditReader().find( SetRefIngEntity.class, ing2_id, 1 );

		// It should be exactly the same object
		assert ing1_rev1.getReference() == ed1_rev1;
		assert ing2_rev1.getReference() == ed1_rev1;
	}
