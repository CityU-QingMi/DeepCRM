	@Test
	@SuppressWarnings( {""})
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testRecursiveMergeTransient() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Employer jboss = new Employer();
		Employee gavin = new Employee();
		jboss.setEmployees( new ArrayList() );
		jboss.getEmployees().add( gavin );
		s.merge( jboss );
		s.flush();
		jboss = (Employer) s.createQuery("from Employer e join fetch e.employees").uniqueResult();
		assertTrue( Hibernate.isInitialized( jboss.getEmployees() ) );
		assertEquals( 1, jboss.getEmployees().size() );
		s.clear();
		s.merge( jboss.getEmployees().iterator().next() );
		tx.commit();
		s.close();

		cleanup();
	}
