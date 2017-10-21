	private void check(
			Class<? extends Dialect> dialectClass,
			Class entityClass,
			Class<? extends Type> binaryTypeClass,
			Class<? extends Type> charTypeClass,
			boolean preferLongRaw) {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.DIALECT, dialectClass.getName() )
				.applySetting( Oracle12cDialect.PREFER_LONG_RAW, Boolean.toString( preferLongRaw ) )
				.applySetting( "hibernate.temp.use_jdbc_metadata_defaults", false )
				.build();

		try {
			final MetadataImplementor mappings = (MetadataImplementor) new MetadataSources( ssr )
					.addAnnotatedClass( entityClass )
					.buildMetadata();
			mappings.validate();

			final PersistentClass entityBinding = mappings.getEntityBinding( entityClass.getName() );

			assertThat( entityBinding.getProperty( "binaryData" ).getType(), instanceOf( binaryTypeClass ) );
			assertThat( entityBinding.getProperty( "characterData" ).getType(), instanceOf( charTypeClass ) );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
