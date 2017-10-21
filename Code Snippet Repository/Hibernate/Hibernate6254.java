      @Test
	public void testAnnReferencesHbm() throws Exception {
		Configuration cfg = new Configuration();
		cfg.configure( "org/hibernate/test/annotations/hibernate.cfg.xml" );
		cfg.addAnnotatedClass( Port.class );
		cfg.setProperty( Environment.HBM2DDL_AUTO, "create-drop" );
		SessionFactory sf = cfg.buildSessionFactory();
		assertNotNull( sf );
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery( "from Boat" );
		assertEquals( 0, q.list().size() );
		q = s.createQuery( "from Port" );
		assertEquals( 0, q.list().size() );
		tx.commit();
		s.close();
		sf.close();
	}
