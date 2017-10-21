	@Test
	@SuppressWarnings( {""})
	public void testSelectClauseImplicitJoin() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Zoo zoo = new Zoo();
		zoo.setName("The Zoo");
		zoo.setMammals( new HashMap() );
		zoo.setAnimals( new HashMap() );
		Mammal plat = new Mammal();
		plat.setBodyWeight( 11f );
		plat.setDescription( "Platypus" );
		plat.setZoo( zoo );
		plat.setSerialNumber( "plat123" );
		zoo.getMammals().put( "Platypus", plat );
		zoo.getAnimals().put("plat123", plat);
		s.persist( plat );
		s.persist(zoo);
		s.flush();
		s.clear();
		Query q = s.createQuery("select distinct a.zoo from Animal a where a.zoo is not null");
		Type type = q.getReturnTypes()[0];
		assertTrue( type instanceof ManyToOneType );
		assertEquals( ( (ManyToOneType) type ).getAssociatedEntityName(), "org.hibernate.test.hql.Zoo" );
		zoo = (Zoo) q.list().get(0);
		assertEquals( zoo.getMammals().size(), 1 );
		assertEquals( zoo.getAnimals().size(), 1 );
		s.clear();
		s.delete(plat);
		s.delete(zoo);
		t.commit();
		s.close();
	}
