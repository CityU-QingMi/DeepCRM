	@Test
	public void testManyToOne() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		One one = new One();
		s.save(one);
		one.setValue( "yada" );
		Many many = new Many();
		many.setOne( one );
		s.save( many );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		one = (One) s.load( One.class, one.getKey() );
		one.getManies().size();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		many = (Many) s.load( Many.class, many.getKey() );
		assertTrue( "many-to-one assoc", many.getOne()!=null );
		s.delete( many.getOne() );
		s.delete(many);
		s.getTransaction().commit();
		s.close();
	}
