	@Test
	public void testFetchInitializedCollectionDupe() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		Collection fooBag = new ArrayList();
		fooBag.add( new Foo() );
		fooBag.add( new Foo() );
		baz.setFooBag(fooBag);
		s.save( baz );
		s.flush();
		fooBag = baz.getFooBag();
		s.createQuery( "from Baz baz left join fetch baz.fooBag" ).list();
		assertTrue( Hibernate.isInitialized( fooBag ) );
		assertTrue( fooBag == baz.getFooBag() );
		assertTrue( baz.getFooBag().size() == 2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		baz = (Baz) s.load( Baz.class, baz.getCode() );
		Object bag = baz.getFooBag();
		assertFalse( Hibernate.isInitialized(bag) );
		s.createQuery( "from Baz baz left join fetch baz.fooBag" ).list();
		assertTrue( Hibernate.isInitialized( bag ) );
		assertTrue( bag==baz.getFooBag() );
		assertTrue( baz.getFooBag().size()==2 );
		s.delete(baz);
		s.getTransaction().commit();
		s.close();
	}
