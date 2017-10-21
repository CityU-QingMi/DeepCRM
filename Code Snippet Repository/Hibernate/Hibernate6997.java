	@Test
	@TestForIssue( jiraKey = "" )
	public void testUnNamedConstraints() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			final Metadata metadata = new MetadataSources( ssr )
					.addAnnotatedClass( UniqueNoNameA.class )
					.addAnnotatedClass( UniqueNoNameB.class )
					.buildMetadata();

			org.hibernate.mapping.Table tableA = null;
			org.hibernate.mapping.Table tableB = null;
			for ( org.hibernate.mapping.Table table : metadata.collectTableMappings() ) {
				if ( table.getName().equals( "UniqueNoNameA" ) ) {
					tableA = table;
				}
				else if ( table.getName().equals( "UniqueNoNameB" ) ) {
					tableB = table;
				}
			}

			assertTrue( "Could not find the expected tables.", tableA != null && tableB != null );
			assertFalse(
					tableA.getUniqueKeyIterator().next().getName().equals(
							tableB.getUniqueKeyIterator().next().getName()
					)
			);
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
