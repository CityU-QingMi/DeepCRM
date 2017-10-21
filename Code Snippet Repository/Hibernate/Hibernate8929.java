	@Test
	@SuppressWarnings( {""})
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testBasic() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Employer er = new Employer();
		Employee ee = new Employee();
		s.persist(ee);
		Collection erColl = new ArrayList();
		Collection eeColl = new ArrayList();
		erColl.add(ee);
		eeColl.add(er);
		er.setEmployees(erColl);
		ee.setEmployers(eeColl);
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		er = (Employer) s.load(Employer.class, er.getId() );
		assertNotNull(er);
		assertNotNull( er.getEmployees() );
		assertEquals( 1, er.getEmployees().size() );
		Employee eeFromDb = (Employee) er.getEmployees().iterator().next();
		assertEquals( ee.getId(), eeFromDb.getId() );
		tx.commit();
		s.close();
	}
