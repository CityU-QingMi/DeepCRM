	@Test
	public void testHistoryOfType() {
		// given
		ClassType type = new ClassType( typeId, "initial description" );

		// when
		ClassType ver1 = getAuditReader().find( ClassType.class, typeId, 1 );

		// then
		Assert.assertEquals( type, ver1 );
		Assert.assertEquals( type.getDescription(), ver1.getDescription() );

		// given
		type.setDescription( "modified description" );

		// when
		ClassType ver2 = getAuditReader().find( ClassType.class, typeId, 2 );

		// then
		Assert.assertEquals( type, ver2 );
		Assert.assertEquals( type.getDescription(), ver2.getDescription() );
	}
