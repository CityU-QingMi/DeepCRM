	@Before
	public void setup(){
		cfg=new Configuration();
		Properties p = new Properties();
		p.put( Environment.DIALECT, "org.hibernate.dialect.HSQLDialect" );
		p.put( "hibernate.connection.driver_class", "org.h2.Driver" );
		p.put( "hibernate.connection.url", "jdbc:h2:mem:" );
		p.put( "hibernate.connection.username", "sa" );
		p.put( "hibernate.connection.password", "" );
		cfg.setProperties(p);
		serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( cfg.getProperties() );
	}
