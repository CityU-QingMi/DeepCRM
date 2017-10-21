	@Test
	public void testArraysOfTimes() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz() ;
		s.save(baz);
		baz.setDefaults();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		baz.getTimeArray()[2] = new Date(123);
		baz.getTimeArray()[3] = new java.sql.Time(1234);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		baz = (Baz) s.load( Baz.class, baz.getCode() );
		s.delete( baz );
		s.getTransaction().commit();
		s.close();
	}
