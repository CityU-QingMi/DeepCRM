	@Test
	public void testManyToManyBag() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		Serializable id = s.save(baz);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		baz = (Baz) s.load(Baz.class, id);
		baz.getFooBag().add( new Foo() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		baz = (Baz) s.load(Baz.class, id);
		assertTrue( !Hibernate.isInitialized( baz.getFooBag() ) );
		assertTrue( baz.getFooBag().size()==1 );
		if ( !(getDialect() instanceof HSQLDialect) ) assertTrue( Hibernate.isInitialized( baz.getFooBag().iterator().next() ) );
		s.delete(baz);
		s.getTransaction().commit();
		s.close();
	}
