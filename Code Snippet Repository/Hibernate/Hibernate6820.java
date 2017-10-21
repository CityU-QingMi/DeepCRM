	@Test
	public void testManyToOneFromPk() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Company company = new Company();
		s.persist( company );

		Person person = new Person();
		person.setDefaultFlag( "T" );
		person.setCompanyId( company.getId() );
		s.persist( person );

		s.flush();
		s.clear();

		company = ( Company ) s.get( Company.class, company.getId() );
		assertNotNull( company.getDefaultContactPerson() );
		assertEquals( person.getId(), company.getDefaultContactPerson().getId() );
		tx.rollback();
		s.close();
	}
