	@Test
	public void testJoinedSubclass() throws HibernateException, SQLException {
		Session s = openSession();
		s.beginTransaction();
		Medication m = new Medication();
		m.setPrescribedDrug(new Drug());
		m.getPrescribedDrug().setName( "Morphine" );
		s.save( m.getPrescribedDrug() );
		s.save( m );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Medication m2  = (Medication) s.get(Medication.class, m.getId());
		assertNotSame(m, m2);
		s.getTransaction().commit();
		s.close();
	}
