	@Test
	public void testHistoryOfTransitiveOverrideEntity() {
		TransitiveOverrideEntity ver1 = new TransitiveOverrideEntity(
				"data 1",
				1,
				transitiveEntityId,
				"data 2",
				2,
				"data 3"
		);
		Assert.assertEquals( ver1, getAuditReader().find( TransitiveOverrideEntity.class, transitiveEntityId, 2 ) );
	}
