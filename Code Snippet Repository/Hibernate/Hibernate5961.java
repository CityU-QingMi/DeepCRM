	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testBasic() throws Exception {

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Employer er = new Employer();
		Employee ee = new Employee();
		em.persist( ee );
		Collection<Employee> erColl = new ArrayList<Employee>();
		Collection<Employer> eeColl = new ArrayList<Employer>();
		erColl.add( ee );
		eeColl.add( er );
		er.setEmployees( erColl );
		ee.setEmployers( eeColl );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		er = em.find( Employer.class, er.getId() );
		assertNotNull( er );
		assertNotNull( er.getEmployees() );
		assertEquals( 1, er.getEmployees().size() );
		Employee eeFromDb = ( Employee ) er.getEmployees().iterator().next();
		assertEquals( ee.getId(), eeFromDb.getId() );
		em.getTransaction().commit();
		em.close();
	}
