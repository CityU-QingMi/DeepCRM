	@Test
	public void testImportTypeDefinitions() throws Exception {
		LastName lastName = new LastName();
		lastName.setName("reddy");
				
		Name name = new Name();
		name.setFirstName("SHARATH");
		name.setLastName(lastName);
		
		FormalLastName formalName = new FormalLastName();
		formalName.setLastName(lastName);
		formalName.setDesignation("Mr");
				
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.persist(name);
		s.persist(formalName);
		tx.commit();
		s.close();
		 
		s = openSession();
		tx = s.beginTransaction();
		name = (Name) s.get( Name.class, name.getId() );
		assertNotNull( name );
		assertEquals( "sharath", name.getFirstName() );
		assertEquals( "REDDY", name.getLastName().getName() );
		
		formalName = (FormalLastName) s.get(FormalLastName.class, formalName.getId());
		assertEquals( "REDDY", formalName.getLastName().getName() );
		
		s.delete(name);
		s.delete(formalName);
		tx.commit();
		s.close();
	}
