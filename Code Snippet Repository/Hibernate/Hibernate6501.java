	@Test
	public void testEmbeddedAndOneToMany() throws Exception {
		Session s;
		s = openSession();
		Transaction tx = s.beginTransaction();
		InternetProvider provider = new InternetProvider();
		provider.setBrandName( "Fido" );
		LegalStructure structure = new LegalStructure();
		structure.setCountry( "Canada" );
		structure.setName( "Rogers" );
		provider.setOwner( structure );
		s.persist( provider );
		Manager manager = new Manager();
		manager.setName( "Bill" );
		manager.setEmployer( provider );
		structure.getTopManagement().add( manager );
		s.persist( manager );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		provider = (InternetProvider) s.get( InternetProvider.class, provider.getId() );
		assertNotNull( provider.getOwner() );
		Set<Manager> topManagement = provider.getOwner().getTopManagement();
		assertNotNull( "OneToMany not set", topManagement );
		assertEquals( "Wrong number of elements", 1, topManagement.size() );
		manager = topManagement.iterator().next();
		assertEquals( "Wrong element", "Bill", manager.getName() );
		s.delete( manager );
		s.delete( provider );
		tx.commit();
		s.close();
	}
