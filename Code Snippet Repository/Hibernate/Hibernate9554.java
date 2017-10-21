	@Test
	public void testCheckForNotExistingForeignKeyOne2One() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchFieldException {
		// Get the private method
		Method method = AbstractSchemaMigrator.class.getDeclaredMethod( "checkForExistingForeignKey", ForeignKey.class, TableInformation.class );
		method.setAccessible( true );

		ForeignKey foreignKey = new ForeignKey();
		foreignKey.setName( "objectId2id_1" ); // Make sure the match is not successful based on key name
		foreignKey.addColumn( new Column( "id" ) );
		foreignKey.setReferencedTable( new Table( "table2" ) );

		Name schemaName = new Name( new Identifier( "-", false ), new Identifier( "-", false ) );
		InformationExtractor informationExtractor = Mockito.mock( InformationExtractor.class );
		IdentifierHelper identifierHelper = new IdentifierHelperImpl();
		List<ForeignKeyInformation> fks = new ArrayList<>();
		fks.add( getForeignKeyInformation( "table2", "blah", "blahKey_001" ) );
		fks.add( getForeignKeyInformation( "table3", "id", "blahKey_002" ) );
		fks.add( getForeignKeyInformation( "table3", "blah", "blahKey_003" ) );
		Mockito.when( informationExtractor.getForeignKeys( Mockito.any() ) ).thenReturn( fks );
		QualifiedTableName tableName = new QualifiedTableName( schemaName, new Identifier( "-", false ) );
		TableInformation tableInformation = new TableInformationImpl( informationExtractor, identifierHelper, tableName, false, null );
		AbstractSchemaMigrator schemaMigrator = new SchemaMigrator();

		// Check single-column-key to single-column-key, existing (table1.objectId => table2.id)
		boolean found = (boolean) method.invoke( schemaMigrator, foreignKey, tableInformation );
		Assert.assertFalse( "Key should not be found", found );
	}
