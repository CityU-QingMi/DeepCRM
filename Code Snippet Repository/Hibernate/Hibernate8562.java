	@Test
	public void testAny() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		One one = new One();
		BarProxy foo = new Bar();
		foo.setObject(one);
		Serializable fid = s.save(foo);
		Serializable oid = one.getKey();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		List results = s.createQuery( "from Bar bar where bar.object.id = ? and bar.object.class = ?" )
				.setParameter( 0, oid, StandardBasicTypes.LONG )
				.setParameter( 1, new Character('O'), StandardBasicTypes.CHARACTER )
				.list();
		assertEquals( 1, results.size() );
		results = s.createQuery( "select one from One one, Bar bar where bar.object.id = one.id and bar.object.class = 'O'" )
				.list();
		assertEquals( 1, results.size() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		foo = (BarProxy) s.load(Foo.class, fid);
		assertTrue( foo.getObject()!=null && foo.getObject() instanceof One && s.getIdentifier( foo.getObject() ).equals(oid) );
		//s.delete( foo.getObject() );
		s.delete(foo);
		s.getTransaction().commit();
		s.close();
	}
