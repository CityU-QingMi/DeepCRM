	@Test
	public void testKeyWithSameNameExists()
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchFieldException {
		// Get the private method
		Method method = AbstractSchemaMigrator.class.getDeclaredMethod( "checkForExistingForeignKey", ForeignKey.class, TableInformation.class );
		method.setAccessible( true );

		ForeignKey foreignKey = new ForeignKey();
		foreignKey.setName( "objectId2id" );
		foreignKey.addColumn( new Column( "id" ) );
		foreignKey.setReferencedTable( new Table( "table2" ) );

		InformationExtractor informationExtractor = Mockito.mock( InformationExtractor.class );
		IdentifierHelper identifierHelper = new IdentifierHelperImpl();
		List<ForeignKeyInformation> fks = new ArrayList<>();
		fks.add( new ForeignKeyInformationImpl( new Identifier( "objectId2id", false ), new ArrayList<>() ) );
		Mockito.when( informationExtractor.getForeignKeys( Mockito.any() ) ).thenReturn( fks );
		Name schemaName = new Name( new Identifier( "-", false ), new Identifier( "-", false ) );
		QualifiedTableName tableName = new QualifiedTableName( schemaName, new Identifier( "-", false ) );
		TableInformation tableInformation = new TableInformationImpl( informationExtractor, identifierHelper, tableName, false, null );

		// foreignKey name with same name should match
		boolean found = (boolean) method.invoke( new SchemaMigrator(), foreignKey, tableInformation );
		Assert.assertTrue( "Key should be found", found );
	}
