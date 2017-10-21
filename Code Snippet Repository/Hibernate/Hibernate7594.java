	@Test
	@SuppressWarnings( {""})
	public void testCascadeAllDeleteOrphanFromClearedPersistentAssnToTransientEntity() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Address address = new Address();
		address.setInfo( "123 Main St." );
		Suite suite = new Suite();
		address.getSuites().add( suite );
		s.save( address );
		t.commit();
		s.close();


		s = openSession();
		t = s.beginTransaction();
		Note note = new Note();
		note.setDescription( "a description" );
		suite.getNotes().add( note );
		address.getSuites().clear();
		s.saveOrUpdate( address );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		Long count = ( Long ) s.createQuery( "select count(*) from Suite" ).list().get( 0 );
		assertEquals( "all-delete-orphan not cascaded properly to cleared persistent collection entities", 0, count.longValue() );
		count = ( Long ) s.createQuery( "select count(*) from Note" ).list().get( 0 );
		assertEquals( 0, count.longValue() );
		s.delete( address );
		t.commit();
		s.close();
	}
