	@Test
	public void testHistoryOfAudited() {
		AuditedMethodSubclassEntity ver1 = new AuditedMethodSubclassEntity( id1_1, "ae", "super str", "audited str" );
		AuditedMethodSubclassEntity ver2 = new AuditedMethodSubclassEntity(
				id1_1,
				"ae new",
				"super str",
				"audited str new"
		);

		AuditedMethodSubclassEntity rev1 = getAuditReader().find( AuditedMethodSubclassEntity.class, id1_1, 1 );
		AuditedMethodSubclassEntity rev2 = getAuditReader().find( AuditedMethodSubclassEntity.class, id1_1, 2 );

		assert (rev1.getOtherStr() != null);
		assert (rev2.getOtherStr() != null);

		assert rev1.equals( ver1 );
		assert rev2.equals( ver2 );
	}
