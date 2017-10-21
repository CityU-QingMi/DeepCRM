    @Test
	public void testPrecedenceHbm() throws Exception {
		Configuration cfg = new Configuration();
		cfg.configure( "org/hibernate/test/annotations/hibernate.cfg.xml" );
		cfg.setProperty( Environment.HBM2DDL_AUTO, "create-drop" );
		cfg.addAnnotatedClass( Boat.class );
		SessionFactory sf = cfg.buildSessionFactory();
		assertNotNull( sf );
		Session s = sf.openSession();
		s.getTransaction().begin();
		Boat boat = new Boat();
		boat.setSize( 12 );
		boat.setWeight( 34 );
		s.persist( boat );
		s.getTransaction().commit();
		s.clear();
		Transaction tx = s.beginTransaction();
		boat = (Boat) s.get( Boat.class, boat.getId() );
		assertTrue( "Annotation has precedence", 34 != boat.getWeight() );
		s.delete( boat );
		//s.getTransaction().commit();
		tx.commit();
		s.close();
		sf.close();
	}
