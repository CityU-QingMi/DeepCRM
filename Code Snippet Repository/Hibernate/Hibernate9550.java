	@Test
	public void testMissingTableInformation()
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Get the private method
		Method method = AbstractSchemaMigrator.class.getDeclaredMethod( "checkForExistingForeignKey", ForeignKey.class, TableInformation.class );
		method.setAccessible( true );

		// foreignKey name with same name should match
		ForeignKey foreignKey = new ForeignKey();
		foreignKey.setName( "objectId2id" );
		boolean found = (boolean) method.invoke( new SchemaMigrator(), foreignKey, null );
		Assert.assertFalse( "Key should not be found", found );
	}
