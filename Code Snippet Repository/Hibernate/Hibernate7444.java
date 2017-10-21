	@Test
	@TestForIssue( jiraKey = "")
	public void basicTest() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();
		try {
			Metadata metadata = new MetadataSources( ssr ).addAnnotatedClass( TestEntity.class ).buildMetadata();
			( (MetadataImpl) metadata ).validate();

			final PersistentClass entityBinding = metadata.getEntityBinding( TestEntity.class.getName() );
			if(metadata.getDatabase().getDialect() instanceof PostgreSQL81Dialect){
				// See issue HHH-10693
				assertEquals(
						Types.VARCHAR,
						entityBinding.getProperty( "name" ).getType().sqlTypes( metadata )[0]
				);
			}else {
				assertEquals(
						Types.NVARCHAR,
						entityBinding.getProperty( "name" ).getType().sqlTypes( metadata )[0]
				);
			}
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
