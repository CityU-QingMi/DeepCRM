	@Test
	public void testHistory1() throws Exception {
		M2MIndexedListTargetNotAuditedEntity rev1 = getAuditReader().find(
				M2MIndexedListTargetNotAuditedEntity.class,
				itnae1_id,
				1
		);
		M2MIndexedListTargetNotAuditedEntity rev2 = getAuditReader().find(
				M2MIndexedListTargetNotAuditedEntity.class,
				itnae1_id,
				2
		);
		M2MIndexedListTargetNotAuditedEntity rev3 = getAuditReader().find(
				M2MIndexedListTargetNotAuditedEntity.class,
				itnae1_id,
				3
		);

		assertTrue( checkCollection( rev1.getReferences(), uste1, uste2 ) );
		assertTrue( checkCollection( rev2.getReferences(), uste1, uste2 ) );
		assertTrue( checkCollection( rev3.getReferences(), uste2, uste1 ) );
	}
