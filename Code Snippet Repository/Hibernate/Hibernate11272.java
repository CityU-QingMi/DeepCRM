	@Test
	public void testHistoryOfClassOverrideNotAuditedEntity() {
		ClassOverrideNotAuditedEntity ver1 = new ClassOverrideNotAuditedEntity(
				null,
				null,
				classNotAuditedEntityId,
				"data 2"
		);
		Assert.assertEquals(
				ver1, getAuditReader().find(
				ClassOverrideNotAuditedEntity.class,
				classNotAuditedEntityId,
				2
		)
		);
	}
