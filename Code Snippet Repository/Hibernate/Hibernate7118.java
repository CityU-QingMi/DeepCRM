	@Test
	public void testQualifiedNameSeparator() throws Exception {
		Namespace.Name namespaceName = new Namespace.Name(
				Identifier.toIdentifier( "DB1" ),
				Identifier.toIdentifier( "PUBLIC" )
		);

		String expectedName = null;

		for ( Namespace namespace : metadata().getDatabase().getNamespaces() ) {
			if ( !namespace.getName().equals( namespaceName ) ) {
				continue;
			}

			assertEquals( 1, namespace.getTables().size() );

			expectedName = metadata().getDatabase().getJdbcEnvironment().getQualifiedObjectNameFormatter().format(
					namespace.getTables().iterator().next().getQualifiedTableName(),
					getDialect()
			);
		}

		assertNotNull( expectedName );

		SingleTableEntityPersister persister = (SingleTableEntityPersister) sessionFactory().getEntityPersister( Box.class.getName() );
		assertEquals( expectedName, persister.getTableName() );
	}
