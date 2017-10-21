	@Test
	public void testHistoryOfId1() {
		// Revision 1
		NotAuditedDynamicMapComponent entity = new NotAuditedDynamicMapComponent( 1L, "static field value" );
		NotAuditedDynamicMapComponent ver1 = getAuditReader().find(
				NotAuditedDynamicMapComponent.class,
				entity.getId(),
				1
		);
		Assert.assertEquals( entity, ver1 );
		// Assume empty NotAuditedDynamicMapComponent#customFields map, because dynamic-component is not audited.
		Assert.assertTrue( ver1.getCustomFields().isEmpty() );

		// Revision 2
		entity.setNote( "updated note" );
		NotAuditedDynamicMapComponent ver2 = getAuditReader().find(
				NotAuditedDynamicMapComponent.class,
				entity.getId(),
				2
		);
		Assert.assertEquals( entity, ver2 );
		// Assume empty NotAuditedDynamicMapComponent#customFields map, because dynamic-component is not audited.
		Assert.assertTrue( ver2.getCustomFields().isEmpty() );
	}
