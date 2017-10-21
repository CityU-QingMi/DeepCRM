     @Test
	public void testConfigurationMethods() throws Exception {
		Configuration ac = new Configuration();
		Properties p = new Properties();
		p.put( Environment.DIALECT, "org.hibernate.dialect.HSQLDialect" );
		p.put( "hibernate.connection.driver_class", "org.hsqldb.jdbcDrive" );
		p.put( "hibernate.connection.url", "jdbc:hsqldb:." );
		p.put( "hibernate.connection.username", "sa" );
		p.put( "hibernate.connection.password", "" );
		p.put( "hibernate.show_sql", "true" );
		ac.setProperties( p );
		ac.addAnnotatedClass( Plane.class );
		SessionFactory sf=null;
		ServiceRegistry serviceRegistry = null;
		try {
			serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( p );
			sf = ac.buildSessionFactory( serviceRegistry );
			try {
				sf.close();
			}
			catch (Exception ignore) {
			}
            Assert.fail( "Driver property overriding should work" );
		}
		catch (HibernateException he) {
			//success
		}
		finally {
			if(sf!=null){
				sf.close();
			}
			if ( serviceRegistry != null ) {
				ServiceRegistryBuilder.destroy( serviceRegistry );
			}
		}
	}
