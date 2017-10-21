	@Test
	@TestForIssue( jiraKey = "" )
	public void testJpaJoinColumnPhysicalNaming() {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySettings( Environment.getProperties() )
				.build();
		try {
			final MetadataSources metadataSources = new MetadataSources( ssr );
			metadataSources.addAnnotatedClass( Language.class );

			final MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
			metadataBuilder.applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE );
			metadataBuilder.applyPhysicalNamingStrategy( PhysicalNamingStrategyImpl.INSTANCE );

			final Metadata metadata = metadataBuilder.build();
			( ( MetadataImplementor) metadata ).validate();

			final PersistentClass languageBinding = metadata.getEntityBinding( Language.class.getName() );
			final Property property = languageBinding.getProperty( "fallBack" );
			Iterator itr = property.getValue().getColumnIterator();
			assertTrue( itr.hasNext() );
			final Column column = (Column) itr.next();
			assertFalse( itr.hasNext() );

			assertEquals( "C_FALLBACK_ID", column.getName().toUpperCase( Locale.ROOT ) );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
