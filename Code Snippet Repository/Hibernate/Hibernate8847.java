	@Test
	@TestForIssue( jiraKey = "" )
	public void testSettingOnCharType() {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.USE_NATIONALIZED_CHARACTER_DATA, true )
				.build();

		try {
			final MetadataSources ms = new MetadataSources( ssr );
			ms.addAnnotatedClass( NationalizedBySettingEntity.class );

			final Metadata metadata = ms.buildMetadata();
			final PersistentClass pc = metadata.getEntityBinding( NationalizedBySettingEntity.class.getName() );
			final Property nameAttribute = pc.getProperty( "flag" );
			if(metadata.getDatabase().getDialect() instanceof PostgreSQL81Dialect ){
				assertSame( CharacterType.INSTANCE, nameAttribute.getType() );
			}else {
				assertSame( CharacterNCharType.INSTANCE, nameAttribute.getType() );
			}

		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
