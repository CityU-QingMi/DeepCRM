	@Test
	public void testBasicUsage() {
		Configuration cfg = new Configuration();
		cfg.addAttributeConverter( IntegerToVarcharConverter.class, false );
		cfg.addAnnotatedClass( Tester4.class );
		cfg.setProperty( AvailableSettings.HBM2DDL_AUTO, "create-drop" );
		cfg.setProperty( AvailableSettings.GENERATE_STATISTICS, "true" );

		SessionFactory sf = cfg.buildSessionFactory();

		try {
			Session session = sf.openSession();
			session.beginTransaction();
			session.save( new Tester4( 1L, "steve", 200 ) );
			session.getTransaction().commit();
			session.close();

			sf.getStatistics().clear();
			session = sf.openSession();
			session.beginTransaction();
			session.get( Tester4.class, 1L );
			session.getTransaction().commit();
			session.close();
			assertEquals( 0, sf.getStatistics().getEntityUpdateCount() );

			session = sf.openSession();
			session.beginTransaction();
			Tester4 t4 = (Tester4) session.get( Tester4.class, 1L );
			t4.code = 300;
			session.getTransaction().commit();
			session.close();

			session = sf.openSession();
			session.beginTransaction();
			t4 = (Tester4) session.get( Tester4.class, 1L );
			assertEquals( 300, t4.code.longValue() );
			session.delete( t4 );
			session.getTransaction().commit();
			session.close();
		}
		finally {
			sf.close();
		}
	}
