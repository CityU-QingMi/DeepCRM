	@Test
	@TestForIssue( jiraKey = "" )
	public void testGlobalQuotingSkippingColumnDef() {
		withStandardServiceRegistry(
				true,
				true,
				new TestWork() {
					@Override
					public void doTestWork(StandardServiceRegistry ssr) {
						MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
								.addAnnotatedClass( E2.class )
								.buildMetadata();
						metadata.validate();

						PersistentClass entityBinding = metadata.getEntityBinding( E2.class.getName() );

						org.hibernate.mapping.Column idColumn = extractColumn( entityBinding.getIdentifier().getColumnIterator() );
						assertTrue( !isQuoted( idColumn.getSqlType(), ssr ) );

						org.hibernate.mapping.Column otherColumn = extractColumn( entityBinding.getProperty( "other" ).getColumnIterator() );
						assertTrue( !isQuoted( otherColumn.getSqlType(), ssr ) );
					}
				}
		);
	}
