	@Test
	@TestForIssue(jiraKey = "")
	public void testQueryWithEmbeddedWithNullUsingSubAttributes() throws Exception {
		Person person = new Person();
		Address a = new Address();
		Country c = new Country();
		Country bornCountry = new Country();
		c.setIso2( "DM" );
		c.setName( "Matt Damon Land" );
		bornCountry.setIso2( "US" );
		assertNull( bornCountry.getName() );

		a.address1 = "colorado street";
		a.city = "Springfield";
		a.country = c;
		person.address = a;
		person.bornIn = bornCountry;
		person.name = "Homer";
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.persist( person );
		} );

		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			Person p = (Person) session.createQuery( "from Person p " +
															 "where ( p.bornIn.iso2 is null or p.bornIn.iso2 = :i ) and " +
															 "( p.bornIn.name is null or p.bornIn.name = :n )"
			).setParameter( "i", person.bornIn.getIso2() )
					.setParameter( "n", person.bornIn.getName() )
					.uniqueResult();
			assertNotNull( p );
			assertNotNull( p.address );
			assertEquals( "Springfield", p.address.city );
			assertNotNull( p.address.country );
			assertEquals( "DM", p.address.country.getIso2() );
			assertEquals( "US", p.bornIn.getIso2() );
			assertNull( p.bornIn.getName() );
		} );
	}
