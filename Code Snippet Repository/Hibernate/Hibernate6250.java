     @Test
	public void testIgnoringHbm() throws Exception {
		Configuration cfg = new Configuration();
		cfg.configure( "org/hibernate/test/annotations/hibernate.cfg.xml" );
		cfg.setProperty( Environment.HBM2DDL_AUTO, "create-drop" );
		cfg.setProperty( Configuration.ARTEFACT_PROCESSING_ORDER, "class" );
		SessionFactory sf = cfg.buildSessionFactory();
		assertNotNull( sf );
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		Query q;
		try {
			s.createQuery( "from Boat" ).list();
			fail( "Boat should not be mapped" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QuerySyntaxException.class, e.getCause());
			//all good
		}
		q = s.createQuery( "from Plane" );
		assertEquals( 0, q.list().size() );
		tx.commit();
		s.close();
		sf.close();
	}
