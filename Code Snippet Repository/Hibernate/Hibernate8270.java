	@Test
	public void testIdentifierGeneratorExtendsIdentityGenerator() {
		final MetadataSources sources = new MetadataSources( serviceRegistry() );
		sources.addAnnotatedClass( EntityBean.class );

		final MetadataBuilder builder = sources.getMetadataBuilder();
		final Metadata metadata = builder.build();


		for ( final Namespace ns : metadata.getDatabase().getNamespaces() ) {
			for ( final org.hibernate.mapping.Table table : ns.getTables() ) {
				final KeyValue value = table.getIdentifierValue();
				assertNotNull( "IdentifierValue was null", value );
				assertTrue( value.isIdentityColumn( metadata.getIdentifierGeneratorFactory(), getDialect() ) );
			}
		}
		
		Session s = openSession();
		s.beginTransaction();
		s.save( new EntityBean() );
		s.getTransaction().commit();
		s.close();
	}
