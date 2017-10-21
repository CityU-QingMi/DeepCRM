	@Test
	public void testHistoryOfAudited() {
		AuditedAllSubclassEntity ver1 = new AuditedAllSubclassEntity( id1_1, "ae", "super str", "audited str" );
		AuditedAllSubclassEntity ver2 = new AuditedAllSubclassEntity( id1_1, "ae new", "super str", "audited str new" );

		AuditedAllSubclassEntity rev1 = getAuditReader().find( AuditedAllSubclassEntity.class, id1_1, 1 );
		AuditedAllSubclassEntity rev2 = getAuditReader().find( AuditedAllSubclassEntity.class, id1_1, 2 );

		assert (rev1.getOtherStr() != null);
		assert (rev2.getOtherStr() != null);

		assert rev1.equals( ver1 );
		assert rev2.equals( ver2 );
	}
