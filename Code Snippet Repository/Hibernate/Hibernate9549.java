	@Test
	public void testForeignKeyWithoutName()
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Get the private method
		Method method = AbstractSchemaMigrator.class.getDeclaredMethod( "checkForExistingForeignKey", ForeignKey.class, TableInformation.class );
		method.setAccessible( true );

		// foreignKey name with same name should match
		ForeignKey foreignKey = new ForeignKey();
		TableInformation tableInformation = new TableInformationImpl( null, null, null, false, null );
		boolean found = (boolean) method.invoke( new SchemaMigrator(), foreignKey, tableInformation );
		Assert.assertFalse( "Key should not be found", found );
	}
