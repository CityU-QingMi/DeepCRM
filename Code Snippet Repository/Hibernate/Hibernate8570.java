	@Test
	public void testBagMultipleElements() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Baz baz = new Baz();
		baz.setBag( new ArrayList() );
		baz.setByteBag( new ArrayList() );
		s.save(baz);
		baz.getBag().add("foo");
		baz.getBag().add("bar");
		baz.getByteBag().add( "foo".getBytes() );
		baz.getByteBag().add( "bar".getBytes() );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		//put in cache
		baz = (Baz) s.get( Baz.class, baz.getCode() );
		assertTrue( baz.getBag().size()==2 );
		assertTrue( baz.getByteBag().size()==2 );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		baz = (Baz) s.get( Baz.class, baz.getCode() );
		assertTrue( baz.getBag().size()==2 );
		assertTrue( baz.getByteBag().size()==2 );
		baz.getBag().remove("bar");
 		baz.getBag().add("foo");
 		baz.getByteBag().add( "bar".getBytes() );
		t.commit();
		s.close();

 		s = openSession();
 		t = s.beginTransaction();
 		baz = (Baz) s.get( Baz.class, baz.getCode() );
 		assertTrue( baz.getBag().size()==2 );
 		assertTrue( baz.getByteBag().size()==3 );
 		s.delete(baz);
 		t.commit();
 		s.close();
 	}
