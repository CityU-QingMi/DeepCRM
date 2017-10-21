	@Test
	public void testHistoryOfEntity() {
		// given
		SampleClass entity = new SampleClass( entityId.getId(), entityId.getType(), "initial data" );

		// when
		SampleClass ver1 = getAuditReader().find( SampleClass.class, entityId, 1 );

		// then
		Assert.assertEquals( entity.getId(), ver1.getId() );
		Assert.assertEquals( entity.getSampleValue(), ver1.getSampleValue() );
		Assert.assertEquals( entity.getType().getType(), ver1.getType().getType() );
		Assert.assertEquals( entity.getType().getDescription(), ver1.getType().getDescription() );

		// given
		entity.setSampleValue( "modified data" );
		entity.getType().setDescription( "modified description" );

		// when
		SampleClass ver2 = getAuditReader().find( SampleClass.class, entityId, 3 );

		// then
		Assert.assertEquals( entity.getId(), ver2.getId() );
		Assert.assertEquals( entity.getSampleValue(), ver2.getSampleValue() );
		Assert.assertEquals( entity.getType().getType(), ver2.getType().getType() );
		Assert.assertEquals( entity.getType().getDescription(), ver2.getType().getDescription() );
	}
