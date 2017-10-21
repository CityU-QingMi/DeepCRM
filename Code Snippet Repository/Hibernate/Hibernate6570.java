	@Test
	public void testHHH10128() {
		final Metadata metadata = new MetadataSources( ssr )
				.addAnnotatedClass( Entity.class )
				.addAnnotatedClass( DescriptionEntity.class )
				.addAnnotatedClass( AddressLevel.class )
				.buildMetadata();

		final PersistentClass addressLevelBinding = metadata.getEntityBinding( AddressLevel.class.getName() );

		final Property natureProperty = addressLevelBinding.getProperty( "nature" );
		CustomType customType = assertTyping( CustomType.class, natureProperty.getType() );
		EnumType enumType = assertTyping( EnumType.class, customType.getUserType() );
		assertEquals( Types.VARCHAR, enumType.sqlTypes()[0] );

		SessionFactoryImplementor sf = (SessionFactoryImplementor) metadata.buildSessionFactory();
		try {
			EntityPersister p = sf.getEntityPersister( AddressLevel.class.getName() );
			CustomType runtimeType = assertTyping( CustomType.class, p.getPropertyType( "nature" ) );
			EnumType runtimeEnumType = assertTyping( EnumType.class, runtimeType.getUserType() );
			assertEquals( Types.VARCHAR, runtimeEnumType.sqlTypes()[0] );
		}
		finally {
			sf.close();
		}
	}
