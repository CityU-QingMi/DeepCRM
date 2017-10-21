	@Test
	public void testEmbeddedAndMultipleManyToOne() throws Exception {
		Session s;
		s = openSession();
		Transaction tx = s.beginTransaction();
		CorpType type = new CorpType();
		type.setType( "National" );
		s.persist( type );
		Nationality nat = new Nationality();
		nat.setName( "Canadian" );
		s.persist( nat );
		InternetProvider provider = new InternetProvider();
		provider.setBrandName( "Fido" );
		LegalStructure structure = new LegalStructure();
		structure.setCorporationType( type );
		structure.setCountry( "Canada" );
		structure.setName( "Rogers" );
		provider.setOwner( structure );
		structure.setOrigin( nat );
		s.persist( provider );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		provider = (InternetProvider) s.get( InternetProvider.class, provider.getId() );
		assertNotNull( provider.getOwner() );
		assertNotNull( "Many to one not set", provider.getOwner().getCorporationType() );
		assertEquals( "Wrong link", type.getType(), provider.getOwner().getCorporationType().getType() );
		assertNotNull( "2nd Many to one not set", provider.getOwner().getOrigin() );
		assertEquals( "Wrong 2nd link", nat.getName(), provider.getOwner().getOrigin().getName() );
		s.delete( provider );
		s.delete( provider.getOwner().getCorporationType() );
		s.delete( provider.getOwner().getOrigin() );
		tx.commit();
		s.close();
	}
