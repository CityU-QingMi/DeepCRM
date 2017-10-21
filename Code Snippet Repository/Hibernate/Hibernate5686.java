	@Test
	@TestForIssue(jiraKey = "")
	public void testListIndex() {
		EntityManager em = getOrCreateEntityManager();
		
		em.getTransaction().begin();
		
		Address address1 = new Address();
		address1.setId( "a1" );
		Phone phone1 = new Phone();
		phone1.setId( "p1" );
		phone1.setAddress( address1 );
		Phone phone2 = new Phone();
		phone2.setId( "p2" );
		
		phone2.setAddress( address1 );
		address1.getPhones().add( phone1 );
		address1.getPhones().add( phone2 );
		
		Address address2 = new Address();
		address2.setId( "a2" );
		Phone phone3 = new Phone();
		phone3.setId( "p3" );
		
		phone3.setAddress( address2 );
		address2.getPhones().add( phone3 );
		
		em.persist( phone1 );
		em.persist( phone2 );
		em.persist( phone3 );
		em.persist( address1 );
		em.persist( address2 );
		em.getTransaction().commit();
		em.clear();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Address> criteria = cb.createQuery( Address.class );
		Root<Address> addressRoot = criteria.from( Address.class );
		ListJoin<Address, Phone> phones = addressRoot.join( Address_.phones );
		criteria.where( cb.gt( phones.index(), 0 ) );
		List<Address> results = em.createQuery( criteria ).getResultList();

		assertNotNull( results );
		// Ensure that the "index(phones) > 0" condition was included on the inner join, meaning only address1
		// (> 1 phone) was returned.
		assertEquals( 1, results.size() );
		assertEquals( address1.getId(), results.get( 0 ).getId() );
	}
