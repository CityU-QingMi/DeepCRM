	@Test
	public void testDefaultCompositePk() throws Exception {
		Session s;
		Transaction tx;

		s = openSession();
		tx = s.beginTransaction();
		CatPk catPk = new CatPk();
		catPk.setName( "Minou" );
		catPk.setThoroughbred( "Persan" );
		Cat cat = new Cat();
		cat.setId( catPk );
		cat.setAge( 32 );
		Woman woman = new Woman();
		WomanPk womanPk = new WomanPk();
		womanPk.setFirstName( "Emma" );
		womanPk.setLastName( "Peel" );
		woman.setId( womanPk );
		woman.setCats( new HashSet<Cat>() );
		woman.getCats().add( cat );
		cat.setHumanContacts( new HashSet<Woman>() );
		cat.getHumanContacts().add( woman );
		s.persist( woman );
		s.persist( cat );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		Cat sameCat = (Cat) s.get( Cat.class, cat.getId() );
		assertNotNull( sameCat );
		assertNotNull( sameCat.getHumanContacts() );
		assertEquals( 1, sameCat.getHumanContacts().size() );
		Woman sameWoman = sameCat.getHumanContacts().iterator().next();
		assertEquals( sameWoman.getId().getLastName(), woman.getId().getLastName() );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		sameWoman = (Woman) s.get( Woman.class, woman.getId() );
		assertNotNull( sameWoman );
		assertNotNull( sameWoman.getCats() );
		assertEquals( 1, sameWoman.getCats().size() );
		sameCat = sameWoman.getCats().iterator().next();
		assertEquals( cat.getAge(), sameCat.getAge() );
		tx.commit();
		s.close();
	}
