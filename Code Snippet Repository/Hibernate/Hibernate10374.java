	@Test
	public void testIsEntityClassAuditedForNotAuditedEntity() {

		assert !getAuditReader().isEntityClassAudited( NotAuditedTestEntity.class );

		try {
			getAuditReader().getRevisions( NotAuditedTestEntity.class, 1 );
		}
		catch (NotAuditedException nae) {
			// it's ok because the entity is not audited
			assert true;
		}
	}
