	@Test
	@SuppressWarnings( {""})
	public void testCascadeAllFromClearedPersistentAssnToTransientEntity() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Person p = new Person();
		Address address = new Address();
		address.setInfo( "123 Main St." );
		p.getAddresses().add( address );
		s.save( p );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		Suite suite = new Suite();
		address.getSuites().add( suite );
		p.getAddresses().clear();
		s.saveOrUpdate( p );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		p = ( Person ) s.get( p.getClass(), p.getId() );
		assertEquals( "persistent collection not cleared", 0, p.getAddresses().size() );
		Long count = ( Long ) s.createQuery( "select count(*) from Address" ).list().get( 0 );
		assertEquals( 1, count.longValue() );
		count = ( Long ) s.createQuery( "select count(*) from Suite" ).list().get( 0 );
		assertEquals( 0, count.longValue() );
		s.delete( p );
		t.commit();
		s.close();
	}
