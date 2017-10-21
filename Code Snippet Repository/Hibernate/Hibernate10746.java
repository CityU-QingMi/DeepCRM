	@Test
	public void testHistory2() throws Exception {
		M2MIndexedListTargetNotAuditedEntity rev1 = getAuditReader().find(
				M2MIndexedListTargetNotAuditedEntity.class,
				itnae2_id,
				1
		);
		M2MIndexedListTargetNotAuditedEntity rev2 = getAuditReader().find(
				M2MIndexedListTargetNotAuditedEntity.class,
				itnae2_id,
				2
		);
		M2MIndexedListTargetNotAuditedEntity rev3 = getAuditReader().find(
				M2MIndexedListTargetNotAuditedEntity.class,
				itnae2_id,
				3
		);

		assertNull( rev1 );
		assertTrue( checkCollection( rev2.getReferences(), uste2 ) );
		assertTrue( checkCollection( rev3.getReferences(), uste2 ) );
	}
