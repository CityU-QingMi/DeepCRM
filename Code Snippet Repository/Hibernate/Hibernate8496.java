	@Test
	public void testForeignKeys() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		Foo foo = new Foo();
		List bag = new ArrayList();
		bag.add(foo);
		baz.setIdFooBag(bag);
		baz.setFoo(foo);
		s.save(baz);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		baz = (Baz) s.load( Baz.class, baz.getCode() );
		s.delete(baz);
		s.getTransaction().commit();
		s.close();
	}
