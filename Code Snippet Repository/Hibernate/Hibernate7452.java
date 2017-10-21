	@Test
	public void testBasicTimestampUsage() {
		Configuration cfg = new Configuration();
		cfg.addAttributeConverter( InstantConverter.class, false );
		cfg.addAnnotatedClass( IrrelevantInstantEntity.class );
		cfg.setProperty( AvailableSettings.HBM2DDL_AUTO, "create-drop" );
		cfg.setProperty( AvailableSettings.GENERATE_STATISTICS, "true" );

		SessionFactory sf = cfg.buildSessionFactory();

		try {
			Session session = sf.openSession();
			session.beginTransaction();
			session.save( new IrrelevantInstantEntity( 1L ) );
			session.getTransaction().commit();
			session.close();

			sf.getStatistics().clear();
			session = sf.openSession();
			session.beginTransaction();
			IrrelevantInstantEntity e = (IrrelevantInstantEntity) session.get( IrrelevantInstantEntity.class, 1L );
			session.getTransaction().commit();
			session.close();
			assertEquals( 0, sf.getStatistics().getEntityUpdateCount() );

			session = sf.openSession();
			session.beginTransaction();
			session.delete( e );
			session.getTransaction().commit();
			session.close();
		}
		finally {
			sf.close();
		}
	}
