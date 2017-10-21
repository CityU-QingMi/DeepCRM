	@Test
	public void testFetchList() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		s.save(baz);
		Foo foo = new Foo();
		s.save(foo);
		Foo foo2 = new Foo();
		s.save(foo2);
		s.flush();
		List list = new ArrayList();
		for ( int i=0; i<5; i++ ) {
			Fee fee = new Fee();
			list.add(fee);
		}
		baz.setFees(list);
		list = s.createQuery( "from Foo foo, Baz baz left join fetch baz.fees" ).list();
		assertTrue( Hibernate.isInitialized( ( (Baz) ( (Object[]) list.get(0) )[1] ).getFees() ) );
		s.delete(foo);
		s.delete(foo2);
		s.delete(baz);
		s.getTransaction().commit();
		s.close();
	}
