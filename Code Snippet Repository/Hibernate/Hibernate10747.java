	@Test
	public void testHistoryOfTnae1_id() {
		UnversionedStrTestEntity uste1 = getEntityManager().find( UnversionedStrTestEntity.class, uste1_id );
		UnversionedStrTestEntity uste2 = getEntityManager().find( UnversionedStrTestEntity.class, uste2_id );

		M2MTargetNotAuditedEntity rev1 = getAuditReader().find( M2MTargetNotAuditedEntity.class, tnae1_id, 1 );
		M2MTargetNotAuditedEntity rev2 = getAuditReader().find( M2MTargetNotAuditedEntity.class, tnae1_id, 2 );
		M2MTargetNotAuditedEntity rev3 = getAuditReader().find( M2MTargetNotAuditedEntity.class, tnae1_id, 3 );
		M2MTargetNotAuditedEntity rev4 = getAuditReader().find( M2MTargetNotAuditedEntity.class, tnae1_id, 4 );

		assertTrue( checkCollection( rev1.getReferences() ) );
		assertTrue( checkCollection( rev2.getReferences(), uste1 ) );
		assertTrue( checkCollection( rev3.getReferences(), uste1 ) );
		assertTrue( checkCollection( rev4.getReferences(), uste1, uste2 ) );
	}
