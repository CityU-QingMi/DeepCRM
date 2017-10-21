    @Test
	public void testDuplicateEntityName() throws Exception {
		Configuration cfg = new Configuration();
		cfg.setProperty( Environment.HBM2DDL_AUTO, "create-drop" );
		ServiceRegistry serviceRegistry = null;
		SessionFactory sf = null;
		try {
			cfg.addAnnotatedClass( Flight.class );
			cfg.addAnnotatedClass( org.hibernate.test.annotations.Flight.class );
			cfg.addResource( "org/hibernate/test/annotations/orm.xml" );
			cfg.addResource( "org/hibernate/test/annotations/duplicatedgenerator/orm.xml" );
			serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( cfg.getProperties() );
			sf = cfg.buildSessionFactory( serviceRegistry );
            Assert.fail( "Should not be able to map the same entity name twice" );
		}
		catch (AnnotationException ae) {
			//success
		}
		finally {
			if (sf != null){
				sf.close();
			}
			if ( serviceRegistry != null ) {
				ServiceRegistryBuilder.destroy( serviceRegistry );
			}
		}
	}
