	@Test
	public void testWithUpperCaseNamingStrategy() throws Exception {
		Metadata metadata = new MetadataSources( serviceRegistry )
				.addAnnotatedClass(A.class)
				.getMetadataBuilder()
				.applyPhysicalNamingStrategy( new PhysicalNamingStrategyStandardImpl() {
					@Override
					public Identifier toPhysicalColumnName(
							Identifier name, JdbcEnvironment context) {
						return new Identifier( name.getText().toUpperCase(), name.isQuoted() );
					}
				} )
				.build();

		PersistentClass entityBinding = metadata.getEntityBinding( A.class.getName() );
		assertEquals("NAME",
					 ((Selectable) entityBinding.getProperty( "name" ).getColumnIterator().next()).getText());
		assertEquals("VALUE",
					 ((Selectable) entityBinding.getProperty( "value" ).getColumnIterator().next()).getText());
	}
