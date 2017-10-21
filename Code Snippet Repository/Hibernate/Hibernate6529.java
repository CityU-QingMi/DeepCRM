	@Test
	public void testTypeDefWithoutNameAndDefaultForTypeAttributes() {
		SessionFactory sf = null;
		StandardServiceRegistryImpl ssr = null;
		try {
			Configuration config = new Configuration();
			config.addAnnotatedClass( LocalContactDetails.class );
			ssr = ServiceRegistryBuilder.buildServiceRegistry( config.getProperties() );
			sf = config.buildSessionFactory( ssr );
			fail( "Did not throw expected exception" );
		}
		catch ( AnnotationException ex ) {
			assertEquals(
					"Either name or defaultForType (or both) attribute should be set in TypeDef having typeClass org.hibernate.test.annotations.entity.PhoneNumberType",
					ex.getMessage()
			);
		}
		finally {
			if ( ssr != null ) {
				ssr.destroy();
			}
			if ( sf != null ) {
				sf.close();
			}
		}
	}
