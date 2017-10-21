	@Test
	public void testEntityMappningPropertiesAreNotIgnored() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		DomainAdmin da = new DomainAdmin();
		da.setAdminUser( "admin" );
		da.setDomainName( "org" );

		s.persist( da );
		Query q = s.getNamedQuery( "DomainAdmin.testQuery" );
		assertEquals( 1, q.list().size() );

		tx.rollback();
		s.close();
	}
